package sk.eea.opendata.sesame;

public class Harvester {
    public static void main(String[] args) {
        SesameRepo sesameRepo = new SesameRepo();
        VirtuosoRepo virtuosoRepo = new VirtuosoRepo();

        String queryVirtuosoString = "PREFIX opendata:<http://sk.eea.opendata/2011/02/opendicts#>\n" +
                "\n" +
                "\n" +
                "SELECT *\n" +
                "from <http://opendata.sk/datanest/organizations/> \n" +
                "from <http://opendata.sk/datanest/political_party_donations/>\n" +
                "from <http://opendata.sk/datanest/procurements/>\n" +
                "\n" +
                "{  {  ?supplier opendata:supplier ?someCompany .\n" +
                "    ?donor opendata:donorCompany ?someCompany .  } } order by asc(?supplier ) ";

        virtuosoRepo.run(queryVirtuosoString);

        String querySesameString = "PREFIX dc:<http://purl.org/dc/elements/1.1/>\n" +
                "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX opendata:<http://sk.eea.opendata/2011/02/opendicts#>\n" +
                "PREFIX procurement:<http://opendata.cz/vocabulary/procurement.rdf#>\n" +
                "PREFIX skos:<http://www.w3.org/2004/02/skos/core#>\n" +
                "PREFIX pc:<http://opendata.cz/vocabulary/procurement.rdf#>\n" +
                "SELECT *\n" +
                "WHERE {\n" +
                "   GRAPH <http://opendata.sk/datanest/> {\n" +
                "    ?supplier opendata:supplier ?someCompany .\n" +
                "    ?donor opendata:donorCompany ?someCompany .\n" +
                "  }\n" +
                "} order by asc(?supplier) \n ";




        sesameRepo.run(queryVirtuosoString);
        System.out.println("Finished successfully");
    }
}
