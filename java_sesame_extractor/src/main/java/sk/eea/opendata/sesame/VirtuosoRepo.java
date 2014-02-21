package sk.eea.opendata.sesame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openrdf.OpenRDFException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.resultio.text.csv.SPARQLResultsCSVWriter;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

import org.openrdf.repository.http.HTTPRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParser;
import org.openrdf.rio.Rio;
import virtuoso.sesame2.driver.VirtuosoRepository;

public class VirtuosoRepo
{
    public void run( String queryString ) {
        Repository repo = new VirtuosoRepository("jdbc:virtuoso://localhost:3333/",   "dba",  "dba");

        try {
            repo.initialize();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }


        try {
            RepositoryConnection con = repo.getConnection();
            TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
            tupleQuery.setMaxQueryTime(5);
            FileOutputStream out = new FileOutputStream("e:/eea/comsode/tests/virtuoso.csv");
            SPARQLResultsCSVWriter csvWriter = new SPARQLResultsCSVWriter(out);
            tupleQuery.evaluate(csvWriter);

            con.close();
        } catch (OpenRDFException e) {
            // handle exception
            e.printStackTrace();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void insert(){
        Repository repo = new VirtuosoRepository("jdbc:virtuoso://localhost:3333/",   "dba",  "dba");

        try {
            repo.initialize();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        try {
            RepositoryConnection con = repo.getConnection();
            File file =new File("e:\\eea\\comsode\\working_repo\\module\\RDF_loader\\src\\test\\resources\\org.rdf");
            RDFFormat parser = Rio.getParserFormatForFileName(file.getName());
            con.add(file, "http://opendata.sk",	RDFFormat.RDFXML);
            con.commit();
            con.close();
        } catch (OpenRDFException e) {
            // handle exception
            e.printStackTrace();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
