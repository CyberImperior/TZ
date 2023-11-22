package mingazov.bank.util;

public interface GenerateAccountNumber {
    static Long get() {
        return System.nanoTime() / 100L;
    }
}
