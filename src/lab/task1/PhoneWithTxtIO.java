package lab.task1;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class PhoneWithTxtIO extends PhoneWithFileIO {
    public PhoneWithTxtIO(Phone phone, Call[] calls) {
        super(phone.countryCode, phone.number, phone.operator, calls);
    }

    public PhoneWithTxtIO() {
        super();
    }

    @Override
    public void writeToFile(String filename) throws IOException {
        if (calls == null) {
            return;
        }
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(String.format("%d %s %s\n", countryCode, number, operator));
            for (Call call : getCalls()) {
                writer.write(String.format("%s %s %s", call.getDate(), call.getDuration(), call.getPrice()));
                writer.write(System.lineSeparator());
            }
        }
    }

    @Override
    public void readFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            ArrayList<Call> calls = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");

                if (countryCode == 0 && number == null && operator == null) {
                    countryCode = Integer.parseInt(parts[0]);
                    number = parts[1];
                    operator = parts[2];
                    continue;
                }

                LocalDateTime date = LocalDateTime.parse(parts[0], DateTimeFormatter.ISO_DATE_TIME);
                double duration = Double.parseDouble(parts[1]);
                double price = Double.parseDouble(parts[2]);
                Call call = new Call(date, duration, price);

                calls.add(call);
            }

            setCalls(calls.toArray(new Call[0]));
        }
    }
}

