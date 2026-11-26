## Automatic Issue Number Hook

client-side git hook based on Java that automatically reads an issue number from your branch name.
This hook assumes that branch names look like this: "<issue-number>-name". For example: 10-QR-scanner

The hook prepends the issue number to each commit done in order to create greated traceability between issues and commits.

## Requirement
* The hook runs on Java 11 or higher, ensure you have it installed and added to your PATH
To check that you can run the command:  <br>
```java -version```

## Setup
Git does not sync hooks across clones so you either have to commit the hook to a shared folder or install it locally.

### Shared folder setup
1. Create a ```.githooks``` directory in the root of your repo and add the 2 files  <br>
```.githooks/prepare-commit-hook```  <br>
```.githooks/commit-hook.java```

2. Allow the hook to execute
```chmod +x .githooks/prepare-commit-msg```

3. Instruct Git to use ```.githooks``` in this repo
Keep in mind this must be run by each developer once after cloning the repo
```git config core.hooksPath```

### Local setup
1. place ```prepare-commit-hook``` and ```commit-hook.java``` in ```.git/hooks/``` for your local repository

2. make it executable
```chmod +x .githooks/prepare-commit-msg```


## Files
This hook uses 2 files so that it is able to run on macOS, windows and Linux. Omitting the prepare-commit-hook breaks it when running on Windows.

- **`prepare-commit-hook`**
  Git looks for an executable file with an exact name when you run ```git commit```.
  The shell script locates the hook directory and launches Java on ```commit-hook.java``` while passing the commit message path and arguments.

- **`commit-hook.java`**
  Contains the Java code that checks the current Git branch, extracts the leading number and automatically adds it to each commit message that does not already contain it.
