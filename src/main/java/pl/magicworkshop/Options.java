package pl.magicworkshop;

import pl.magicworkshop.exceptions.InvalidOptionException;

public enum Options {
    ADD_DEVICE(1, "Dodaj urządzenie"),
    ADD_CATEGORY(2, "Dodaj kategorię"),
    ADD_CUSTOMER(3, "Dodaj klienta"),
    RENT(4,"Wypożycz urządzenie"),
    REMOVE_DIVCE(5, "Usuń urządzenie"),
    REMOVE_CATEGORY(6, "Usuń urządzenie"),
    REMOVE_CUSTOMER(7, "Usuń klienta"),
    EXIT(8, "Wyjście");

    private int numb;
    private String desc;

    Options(int numb, String desc){
        this.numb = numb;
        this.desc = desc;
    }

    @Override
    public String toString(){
        return  numb + " - "+ desc;
    }

    static Options numberToOption(int numb) {
        if (numb >= 1 && numb < values().length) {
            return values()[numb - 1];
        }
        throw new InvalidOptionException();
    }
}
