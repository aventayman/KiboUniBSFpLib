package it.ayman.fp.lib;

import java.io.Serializable;

/**
 * The <strong>Menu</strong> class creates a menu with multiple entry supposing
 * that zero is always the exit option. The class also contains some method that
 * may result useful in visualizing the menu.
 *
 * @author Ayman Marpicati
 * @version 1.0
 */
public class Menu implements Serializable {
    private static final String EXIT_ENTRY = "0. Exit";
    private static final String INSERT_REQUEST = "> ";
    private static final String NEGATIVE_MILLIS_ERROR = AnsiColors.RED + "Attention!" + AnsiColors.RESET
            + "\nYou can't have negative time.";

    /**
     * Contains all the menu entries.
     */
    private final String[] entries;

    /**
     * Represent if you want to use the exit entry or not.
     */
    private final boolean useExitEntry;

    /**
     * Constructor that creates a <code>Menu</code> object specifying a title, the
     * entries of the menu, if you want the exit entry or not, if you want the title
     * centred and if you also want the vertical frame in the title. It will also
     * automatically calculate the frame length.
     *
     * @param entries          Represents the entries of the menu.
     * @param useExitEntry     If you want the exit entry or not.
     */
    public Menu(String[] entries, boolean useExitEntry) {
        this.entries = entries;
        this.useExitEntry = useExitEntry;
    }

    /**
     * Clear any character from the console.
     */
    public static void clearConsole() {
        System.out.print(AnsiColors.CLEAR);
        System.out.flush();
    }

    /**
     * Stops the program for a certain amount of milliseconds.
     *
     * @param milliseconds The number of milliseconds to stop the program.
     * @throws InterruptedException If any thread has interrupted the current
     *                              thread. The <i>interrupted status</i> of the
     *                              current thread is cleared when this exception is
     *                              thrown.
     */
    public static void wait(int milliseconds) throws InterruptedException {
        try {
            Thread.sleep(milliseconds);
        } catch (IllegalArgumentException e) {
            System.out.println(NEGATIVE_MILLIS_ERROR);
        }
    }

    /**
     * Prints a certain message simulating a loading by adding dots slowly.
     *
     * @param message The message to print.
     * @throws InterruptedException Read the {@link #wait() wait} method.
     */
    public static void loadingMessage(String message) throws InterruptedException {
        System.out.print(message + ".");
        wait(1000);
        System.out.print(".");
        wait(1000);
        System.out.print(".");
        wait(1000);
        clearConsole();
    }

    private void printMenuItems() {
        StringBuffer menu = new StringBuffer();

        for (int i = 0; i < entries.length; i++)
            menu.append(i != entries.length - 1 ? String.format("%d. %s\n", (i + 1), entries[i])
                    : String.format("%d. %s", (i + 1), entries[i]));

        if (useExitEntry)
            menu.append(PrettyStrings.isolatedLine(EXIT_ENTRY));

        System.out.println(menu);
    }

    /**
     * Prints the menu and lets the user choose an option from it.
     *
     * @return An <code>int</code> representing the choice of the user.
     */
    public int choose() {
        printMenuItems();

        if (useExitEntry)
            return InputData.readIntegerBetween("", 0, entries.length);
        else
            return InputData.readIntegerBetween("", 1, entries.length);
    }
}