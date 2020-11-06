
maven ile JAR paketi oluşturma
```
$ mvn package
```

Jar dosyası aşağıdaki dizinde oluşturulacaktır.
```
target/news-rss-1.0.jar
```


maven-shade-plugin Run time da çalışan bütün class ve jarları maven ile alınan pakete(JAR'a) ekler.
aşağıdaki şekidle basit tanımı yeterlidir.
```
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.0.0</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```
