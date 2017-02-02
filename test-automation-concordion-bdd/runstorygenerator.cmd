
mvn -f StoryGenerator.xml compile exec:java -Dexec.classpathScope=compile   -Dexec.mainClass="StoryGenerator" -Dexec.args="%1 %2 %3 %4 %5 %6" -Dlog4j.configuration=file:./log4j.properties

@rem -f StoryGenerator.xml compile exec:java -Dexec.classpathScope=compile   -Dexec.mainClass="StoryGenerator" -Dexec.args="--packageName com.v2solutions.ellamoss.contactus --storyName EllamossContactUsPage.txt" -Dlog4j.configuration=file:./log4j.properties
