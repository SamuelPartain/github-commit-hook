import java.nio.file.*;
import java.util.regex.*;


public class commitHook {
  public static void main(String[] args) throws Exception{
    // Checks and skips commits that are merges or squashes
    if(args.length < 1 || (args.length > 1 && ("merge".equals(args[1]) || "squash".equals(args[1])))) {
      return;
    }
    
    // Get current branch name
    Process process = new ProcessBuilder("git", "symbolic-ref", "--short", "HEAD").start();
    
    // Read output of process
    // Trims trailing newline character
    String branchName = new String(process.getInputStream().readAllBytes()).trim();

    // Matches branch name against regex pattern
    Matcher match = Pattern.compile("^(\\d+)-").matcher(branchName);

    // Checks if branch name starts with a number followed by dash
    if(!match.find()) {
      return;
    }

    // instansiate prefix commit with issue number to improve traceability
    String prefix = "#" + match.group(1);
    Path msgPath = Paths.get(args[0]);
    String msg = Files.readString(msgPath);

    // Appends issue number to commit message if that is not already manually added
    if(!msg.startsWith(prefix)) {
      Files.writeString(msgPath, prefix + " " + msg);
    }
  }
}
