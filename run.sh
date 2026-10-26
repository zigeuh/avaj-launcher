find * -name "*.java" > sources.txt
javac @sources.txt
java -cp src/ io.github.zigeuh.avaj.Simulator scenario.txt