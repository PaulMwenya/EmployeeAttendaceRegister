import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.PrettyPrinterConfiguration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

    public class Extractor {
        public static void main(String[] args) {
            try {
                // Parse the Java file
                CompilationUnit cu = JavaParser.parse(new FileInputStream("C:/Users/MAETRIX/IdeaProjects/JavaParser/src/EmployeeAttendanceRegister.java"));

                // Create a visitor for extracting lexical details
                LexicalDetailVisitor visitor = new LexicalDetailVisitor();
                visitor.visit(cu, null);

                // Print AST of each class
                System.out.println("Abstract Syntax Tree of each class:");
                for (Node n : cu.getChildNodes()) {
                    System.out.println(n.toString(new PrettyPrinterConfiguration()));
                }

                // Print total number of tokens
                System.out.println("Total number of tokens: " + visitor.getTotalTokens());

                // Print token types and their counts
                System.out.println("Token types and their counts:");
                for (Map.Entry<String, Integer> entry : visitor.getTokenTypes().entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        private static class LexicalDetailVisitor extends VoidVisitorAdapter<Void> {
            private int totalTokens = 0;
            private Map<String, Integer> tokenTypes = new HashMap<>();

            public int getTotalTokens() {
                return totalTokens;
            }

            public Map<String, Integer> getTokenTypes() {
                return tokenTypes;
            }

            @Override
            public void visit(MethodDeclaration md, Void arg) {
                super.visit(md, arg);
                // Print method name and its body
                System.out.println("Method name: " + md.getNameAsString());
                System.out.println("Method body: " + md.getBody().get().toString());

                // Count tokens and their types
                md.findAll(Node.class).forEach(node -> {
                    totalTokens++;
                    String type = node.getClass().getSimpleName();
                    tokenTypes.put(type, tokenTypes.getOrDefault(type, 0) + 1);
                });
            }
        }
    }
