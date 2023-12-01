package lab.task1;

import java.io.*;

public class PhoneWithBinaryIO extends PhoneWithFileIO implements Serializable {
    public PhoneWithBinaryIO(Phone phone, Call[] calls) {
        super(phone.countryCode, phone.number, phone.operator, calls);
    }

    public PhoneWithBinaryIO() {
        super();
    }

    @Override
    public void writeToFile(String filename) throws IOException {
        if (calls == null) {
            return;
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(this);
        }
    }

    @Override
    public void readFromFile(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            return;
        }
        if (file.length() == 0) {
            setCalls(new Call[0]);
            return;
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            PhoneWithBinaryIO phoneWithBinaryIO = (PhoneWithBinaryIO) inputStream.readObject();
            this.countryCode = phoneWithBinaryIO.countryCode;
            this.number = phoneWithBinaryIO.number;
            this.operator = phoneWithBinaryIO.operator;
            this.setCalls(phoneWithBinaryIO.getCalls());
        }
    }
}