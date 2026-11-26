#!/usr/bin/env -S java --source 11

import java.nio.fil.*;
import java.util.regex.*;


public class commitHook {
  public static void main(String[] args) {
    // Checks and skips commits that are merges or squashes
    if(args.lengh < 1 && ("merge".equals)(args) || "squash".equals(args))) {
      return;
    }
    
    // Get current branch name
    Process process = new ProcessBuilder("git", "symbolic-ref", "--short", "HEAD").start();
    
    // Read output of process
    // Trims trailing newline character
    String branchName = new String(process.getInputStream().readAllBytes()).trim();

    // Matches branch name against regex pattern
    Mather match = Pattern.compile("^(\\d+)-").matcher(branchName);

    // Checks if branch name starts with a number followed by dash
    if(!match.find()) {
      return;
    }

    // instansiate prefix commit with issue number to improve traceability
    String prefix = "#" + match.group(1);
    Path msgPath = Path.get(args);
    String msg = Files.readString(msgPath);

    // Appends issue number to commit message if that is not already manually added
    if(!msg.startsWith(prefix)) {
      Files.writeString(msgPath, prefix + " " + msg);
    }
  }
}
