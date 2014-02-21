package sk.eea.opendata.sesame;

public class Harvester {
    public static void main(String[] args) {
        SesameRepo sesameRepo = new SesameRepo();
        VirtuosoRepo virtuosoRepo = new VirtuosoRepo();

        String queryString = "PREFIX dc:<http://purl.org/dc/elements/1.1/>\n" +
                "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX opendata:<http://sk.eea.opendata/2011/02/opendicts#>\n" +
                "PREFIX org:<http://www.w3.org/ns/org#>\n" +
                "PREFIX skos:<http://www.w3.org/2004/02/skos/core#>\n" +
                "PREFIX rov:<http://www.w3.org/TR/vocab-regorg/>\n" +
                "PREFIX locn:<http://www.w3.org/ns/locn#>\n" +
                "PREFIX pc:<http://opendata.cz/vocabulary/procurement.rdf#>\n" +
                "PREFIX adms:<http://www.w3.org/ns/adms#>\n" +
                "PREFIX dcterms:<http://purl.org/dc/terms/>\n" +
                "\n" +
                "SELECT  ?a ?b ?c  from <http://opendata.sk/datanest/procurements/> where    {    ?a  ?b ?c   } order by Asc(?a)  ";
//        sesameRepo.run(queryString);
//        virtuosoRepo.run(queryString);
        virtuosoRepo.insert();
    }
}
