#compile
javac cscie97/asn1/knowledge/engine/*.java cscie97/asn1/test/*.java

#run program with default inputs
java -cp . cscie97.asn1.test.TestDriver inputTriples.nt sampleQuery.nt
