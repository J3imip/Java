package lab.task1;

import java.io.*;

public abstract class PhoneWithFileIO extends Phone implements Serializable {
    protected Call[] calls;

    public PhoneWithFileIO(int countryCode, String number, String operator, Call[] calls) {
        super(countryCode, number, operator);
        this.calls = calls;
    }

    public PhoneWithFileIO() {
        super();
    }

    public void setCalls(Call[] calls) {
        this.calls = calls;
    }

    @Override
    public Call[] getCalls() {
        return calls;
    }

    public abstract void writeToFile(String filename) throws IOException;
    public abstract void readFromFile(String filename) throws IOException, ClassNotFoundException;

    public void testFunctionality() throws IOException, ClassNotFoundException {
        writeToFile("test.txt");
        readFromFile("test.txt");

        writeToFile("test.bin");
        readFromFile("test.bin");
    }
}


