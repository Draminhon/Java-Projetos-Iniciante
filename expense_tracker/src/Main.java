import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.*;
import java.time.MonthDay;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        File file = new File("expenses_tracker.csv");

        try {
            FileReader fileReader = new FileReader(file);
            CSVReader reader = new CSVReader(fileReader);

            Scanner sc = new Scanner(System.in);
            int choice = 0;
            int lines = 0;
            String[] nextRecord;
            while (reader.readNext() != null) {
                lines++;
            }


                String desc, amount;
                System.out.println("1 - para adicionar uma expense \n2- para listar as despessas\n3- para somar tudo \n4 - para excluir uma \n0 - para sair");
                choice = sc.nextInt();
                sc.nextLine();



                if (lines == 0) {
                    initiateExpenseSheet(file);
                }

                if (choice == 1) {
                    System.out.println("Informe a descrição da atividade");
                    desc = sc.nextLine();
                    System.out.println("Informe o custo da atividade");
                    amount = sc.next();
                    addExpense(file, desc, amount, lines);
                }
                if (choice == 2)
                    listExpenses(file);
                if (choice == 3)
                    sumExpenses(file, lines);
                if (choice == 4)
                    deleteExpense(file, "1", lines);

            reader.close();
            sc.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    static void initiateExpenseSheet(File file){
        FileWriter outputfile = null;
        try {
            outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
            String[] header = {"ID", "Date", "Description", "Amount"};
            writer.writeNext(header);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static void addExpense(File file, String desc, String amount, int lines){

        try {
            FileReader readerFile = new FileReader(file);
            CSVReader reader = new CSVReader(readerFile);

            List<String[]> conteudoAtual = reader.readAll();

            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            writer.writeAll(conteudoAtual);

            String ID = lines == 0 ? String.valueOf(lines) : String.valueOf(lines-1) ;


            MonthDay today = MonthDay.now();
            String day = String.valueOf(today.getDayOfMonth());
            String month = String.valueOf(today.getMonthValue());
            String dayMonth = day + "/" + month;
            String[] items = new String[4];
            items[0] = ID;
            items[1] = dayMonth;
            items[2] = desc;
            items[3] = amount;
            writer.writeNext(items);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    static void listExpenses(File file){

        try {
            FileReader reader = new FileReader(file);
            CSVReader csvReader = new CSVReader(reader);

            String[] content;
            while((content = csvReader.readNext()) !=null){
                    for (String cell : content){
                        System.out.print(cell + " ");
                    }
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    static void sumExpenses(File file, int lines){

        double total = 0;

        FileReader reader = null;
        try {
            reader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> actualContent = csvReader.readAll();
            for (int i = 0; i < lines-1; i++) {
                total += Double.parseDouble(actualContent.get(i)[3]);
            }
            System.out.println("Total expenses: R$ " + total );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void deleteExpense(File file, String id, int lines){

        FileReader readerFile = null;
        try {
            readerFile = new FileReader(file);
            CSVReader reader = new CSVReader(readerFile);

            List<String[]> conteudoAtual = reader.readAll();
            System.out.println(conteudoAtual.removeIf(a -> Arrays.stream(a).anyMatch(s -> s.equalsIgnoreCase(id))));
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            writer.writeAll(conteudoAtual);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
