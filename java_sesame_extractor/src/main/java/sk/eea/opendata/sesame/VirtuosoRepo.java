package sk.eea.opendata.sesame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.openrdf.OpenRDFException;
import org.openrdf.model.Resource;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.resultio.text.csv.SPARQLResultsCSVWriter;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.Rio;
import org.openrdf.rio.RDFParser;
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

        String uri = "http://opendata.sk/procurements2";
        Resource context = new URIImpl(uri); //nejde pre bigdata, musi byt null

        try {
            repo.initialize();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        try {
            RepositoryConnection con = repo.getConnection();
            File file =new File("e:\\eea\\comsode\\rdf\\small\\procurements.rdf");

            con.add(file, uri,	RDFFormat.RDFXML, context);
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
