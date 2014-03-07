package sk.eea.opendata.sesame;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openrdf.OpenRDFException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
//import org.openrdf.query.resultio.text.csv.SPARQLResultsCSVWriter;
import org.openrdf.query.resultio.text.csv.SPARQLResultsCSVWriter;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;
import org.openrdf.rio.RDFFormat;

/**
 * Hello world!
 * 
 */
public class SesameRepo {
    public  void run( String queryString) {

        String sesameServer = "http://localhost:8082/openrdf-sesame/";
        String repositoryID = "odn";

        Repository repo = new HTTPRepository(sesameServer, repositoryID);
        try {
            repo.initialize();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        try {
            RepositoryConnection con = repo.getConnection();
            TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
            FileOutputStream out = new FileOutputStream("e:/eea/comsode/tests/sesame.csv");
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


}
