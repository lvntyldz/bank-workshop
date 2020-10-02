#remove old compiled files
rm -rf com/jar/*.class

#remove old jar
rm -rf ../Run.jar

#compile all java codes
javac com/jar/*.java

#generate jar with manifest data
jar cfm ../Run.jar com/jar/manifest.txt com

#run jar file
java -jar ../Run.jar
