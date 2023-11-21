package mingazov.bank.util;

public interface GenerateAccountNumber {
    static Long get() {
        return System.currentTimeMillis();
    }
}
