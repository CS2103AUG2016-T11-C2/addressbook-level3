package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

public class DeleteTaskCommand extends Command{
	 public static final String COMMAND_WORD = "deletetask";

	    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
	            + "Deletes the person identified by the task name used in the listing.\n\t"
	            + "Parameters: NAME\n\t"
	            + "Example: " + COMMAND_WORD + " John";

	    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";


	    public DeleteTaskCommand(String targetVisibleName) {
	        super(targetVisibleName);
	    }


	    @Override
	    public CommandResult execute() {
	        try {
	            final ReadOnlyPerson target = getTargetPerson();
	            addressBook.removePerson(target);
	            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, target));

	        } catch (IndexOutOfBoundsException ie) {
	            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
	        } catch (PersonNotFoundException pnfe) {
	            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
	        }
	    }
}
