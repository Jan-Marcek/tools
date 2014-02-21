# -*- coding: utf-8-*-

import urllib, requests
import urllib2
from win32api import Unicode
from requests.auth import HTTPDigestAuth

headers = {"Content-type": "application/x-www-form-urlencoded",
           "Accept": "*/*", "format": 'application/rdf+xml', "using-graph-uri": 'http://test.sk', 'charset': 'utf-8'}
non_asci_character = Unicode('a')
auth = HTTPDigestAuth('upload', 'upload')

query = u'INSERT  INTO GRAPH <urn:sparql:tests:insert:data>  {<http://data.gov.sk/id/interior/organization/dfsdfaasdfasdfass> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/TR/vocab-regorg/RegisteredOrganization> .<http://data.gov.sk/id/interior/organization/dfsdfaasdfasdfass> <http://www.w3.org/TR/vocab-regorg/legalName> """""" .<http://data.gov.sk/id/interior/organization/dfsdfaasdfasdfass> <http://purl.org/dc/elements/1.1/source> <http://www.statistics.sk/pls/wregis/detail?wxidorg=403> .<http://data.gov.sk/id/interior/organization/dfsdfaasdfasdfass> <http://purl.org/dc/elements/1.1/type> """Družstvo""" .<http://data.gov.sk/id/interior/organization/dfsdfaasdfasdfass> <http://sk.eea.opendata/2011/02/opendicts#dateFrom> """23.03.2006""" .<http://data.gov.sk/id/interior/organization/dfsdfaasdfasdfass> <http://sk.eea.opendata/2011/02/opendicts#dateTo> """23.03.2006""" .<http://data.gov.sk/id/interior/organization/dfsdfaasdfasdfass> <http://www.w3.org/ns/org#registeredSite> _:node18h7u52u6x1 .<http://data.gov.sk/id/interior/organization/dfsdfaasdfasdfass> <http://sk.eea.opendata/2011/02/opendicts#ico> <http://data.gov.sk/id/interior/identifier/dfsdfaasdfasdfass> ._:node18h7u52u6x1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/ns/locn#address> ._:node18h7u52u6x1 <http://www.w3.org/ns/locn#fullAddress> """Priemyselná 2,  918 41 Trnava"""^^<xsd:string> .}  '
data = {'query': query}
r = requests.post("http://localhost:5555/sparql-auth", data=data, auth=auth, headers=headers)
print r.status_code

print r.text

