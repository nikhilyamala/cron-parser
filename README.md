# Building the application
- This is a maven project.
- The project is built using `mvn clean install` command.
- A jar should be created in `target/` folder.

# Running the application
- Command used to run the application `java -jar target/cron-parser-1.0-SNAPSHOT.jar "<input_string>"`

- `input_string` should be passed in this format : [minute] [hour] [day of month] [month] [day of week] [command]

  Example: `*/15 0 1,15 * 1-5 /usr/bin/find`

- Sample output of the application :

```
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
```
