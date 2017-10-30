import org.apache.commons.cli.*;

public class MyList {

    public <T extends Comparable<T>> int binSearch(T[] array, T value) {

        // Source: majority of algorithm comes from Starting out with Java from Control Structures
        // to Data Structures by Tony Gaddis and Godfrey Muganda. I added some code to adapt it to my need.
        int first;
        int last;
        int middle;
        int position;
        boolean found;

        first = 0;
        last = array.length - 1;
        position = -1;
        found = false;

        while (!found && first <= last) {
            middle = (first + last) / 2;

            if (array[middle].compareTo(value) == 0) {
                found = true;
                position = middle;
            } else if (array[middle].compareTo(value) > 0)
                last = middle - 1;
            else
                first = middle + 1;
        }

        if (position < 0)
            return 0;
        else
            return 1;
    }

    public static void main(String[] args) throws ParseException {

        CommandLineParser parser = new DefaultParser();

        Options options = new Options();
        options.addOption(Option.builder("type").longOpt("type").desc("For integer inputs").hasArg().numberOfArgs(1).argName("i").required(true).build());
        options.addOption(Option.builder("type").longOpt("type").desc("For string inputs").hasArg().numberOfArgs(1).argName("s").required(true).build());
        options.addOption(Option.builder("key").longOpt("key").desc("Input key integer to be searched in sorted list").hasArg().numberOfArgs(1).required(true).build());
        options.addOption(Option.builder("list").longOpt("list").desc("Sorted list to be searched").hasArgs().valueSeparator(' ').required(true).build());

        CommandLine cmd = parser.parse(options, args);

        if (cmd.getOptionValue("type").equals("s")) {
            try{
                String key = cmd.getOptionValue("key");
                String[] list = cmd.getOptionValues("list");

                MyList myList = new MyList();
                System.out.println(myList.binSearch(list, key));
            } catch (NumberFormatException e){
                System.out.println("ERROR");
            }
        }
        if(cmd.getOptionValue("type").equals("i")){
            try {
                int key = Integer.parseInt(cmd.getOptionValue("key"));
                String[] list = cmd.getOptionValues("list");
                Integer[] listInts = new Integer[list.length];
                for (int i = 0; i < listInts.length; i++) {
                    listInts[i] = Integer.parseInt(list[i]);
                }
                MyList myList = new MyList();
                System.out.println(myList.binSearch(listInts, key));
            } catch(NumberFormatException e){
                System.out.println("Error: Please enter integers for your key and list value inputs.");
            }
        }
    }
}
