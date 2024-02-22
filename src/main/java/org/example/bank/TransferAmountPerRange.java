package org.example.bank;

public enum TransferAmountPerRange{
    lessThan1Million(600), between1and2Million(840), between2and3Million(1080),
    between3and4Million(1320), between4and5Million(1560), between5and6Million(1800),
    between6and7Million(2040), between7and8Million(2280), between8and9Million(2520),
    between9and10Million(2760), between10and11Million(3000), between11and12Million(3240),
    between12and13Million(3480), between13and14Million(3720), between14and15Million(3960);
    public int value;
    private TransferAmountPerRange(int value) {
        this.value = value;
    }
}
