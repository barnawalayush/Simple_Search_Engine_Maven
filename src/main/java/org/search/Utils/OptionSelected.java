package org.search.Utils;

/**
 * The OptionSelected enum represents different options
 * that can be selected in an application.
 * This enum contains four options: OPTION1,
 * OPTION2, OPTION3, and OPTION4.
 * Each option has an associated integer value
 * that can be retrieved using the getOption method.
 */
public enum OptionSelected {

    /**
     * The OPTION1 option represents the first option.
     */
    OPTION1(1),
    /**
     * The OPTION2 option represents the second option.
     */
    OPTION2(2),
    /**
     * The OPTION2 option represents the second option.
     */
    OPTION3(0),
    /**
     * The OPTION4 option represents the fourth option.
     */
    OPTION4(3);

    /**
     * tell about option.
     */
    private int option;

    /**
     * Constructs a new OptionSelected enum with the specified option value.
     * @param opt The option value.
     */
    OptionSelected(final int opt) {
        this.option = opt;
    }

    /**
     * Returns the option value.
     *
     * @return The option value.
     */
    public int getOption() {
        return option;
    }
}
