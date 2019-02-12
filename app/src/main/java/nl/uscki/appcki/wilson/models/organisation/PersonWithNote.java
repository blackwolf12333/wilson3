package nl.uscki.appcki.wilson.models.organisation;

import java.util.ArrayList;
import java.util.List;

import nl.uscki.appcki.wilson.models.IWilsonBaseItem;
import nl.uscki.appcki.wilson.models.meeting.Preference;
import nl.uscki.appcki.wilson.models.meeting.Slot;

/**
 * Created by peter on 7/30/16.
 */
public class PersonWithNote implements IWilsonBaseItem {
    Integer id;
    PersonSimpleName person;
    String note;

    public PersonWithNote(PersonSimpleName person, String note) {
        this.person = person;
        this.note = note;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public PersonSimpleName getPerson() {
        return person;
    }

    public String getNote() {
        return note;
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof PersonWithNote) {
            PersonWithNote p = (PersonWithNote) o;
            return p.getPerson().equals(this.person);
        }
        return false;
    }

    /*public static List<PersonWithNote> fromEnrolledPersons(List<EnrolledPerson> participants) {
        List<PersonWithNote> persons = new ArrayList<>();
        for (EnrolledPerson person : participants) {
            persons.add(new PersonWithNote(person.getId(), person.getName(), "", person.getPhotomediaid()));
        }
        return persons;
    }*/

    public static List<PersonWithNote> fromSlotPreferencesAvailable(Slot slot) {
        List<PersonWithNote> persons = new ArrayList<>();
        for (Preference p : slot.getPreferences()) {
            if (p.getCanattend()) {
                persons.add(new PersonWithNote(p.getPerson(), p.getNotes()));
            }
        }
        return  persons;
    }

    public static List<PersonWithNote> fromSlotPreferencesUnavailable(Slot slot) {
        List<PersonWithNote> persons = new ArrayList<>();
        for (Preference p : slot.getPreferences()) {
            if (!p.getCanattend()) {
                persons.add(new PersonWithNote(p.getPerson(), p.getNotes()));
            }
        }
        return  persons;
    }
}
