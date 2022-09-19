package src.hack;

public class PriorityHack {
    Hack hackathon;
    int priority;
    String priorityNote;
    
    public PriorityHack  (Hack hackathon, int priority, String priorityNote) {
        this.hackathon = hackathon;
        this.priority = priority;
        this.priorityNote = priorityNote;
    }

    //Getter methods 
    public Hack getHackathon() {
        return hackathon;
    }

    public int getPriorityNo() {
        return priority;
    }

    public String getPriorityNote() {
        return priorityNote;
    }

    //Setter methods
    public void setHackathon (Hack hackathon) {
        this.hackathon = hackathon;
    }

    public void setPriorityNo (int priNo) {
        this.priority = priNo;
    }

    public void setPriorityNote(String priNote) {
        this.priorityNote = priNote;
    }

    public String printString() {
        String returnString = "";
        if (getHackathon().getHackEndDate() == null) {
            returnString = getHackathon().getHackName() + "\nPriority Number: " + getPriorityNo() + "\nNotes: " + getPriorityNote() + "\nDate: " + getHackathon().getHackStartDate() + "\nMode: " + getHackathon().getHackMode() + "\nLocation: " + getHackathon().getHackLocation() + "\nRegistration: " + getHackathon().getHackReg() + "\nLink: " + getHackathon().getHackLink() + "\n";
        }
        else {
            returnString = getHackathon().getHackName() + "\nPriority Number: " + getPriorityNo() + "\nNotes: " + getPriorityNote() + "\nDate: " + getHackathon().getHackStartDate() + "-" + getHackathon().getHackEndDate()+ "\nMode: " + getHackathon().getHackMode() + "\nLocation: " + getHackathon().getHackLocation() + "\nRegistration: " + getHackathon().getHackReg() + "\nLink: " + getHackathon().getHackLink() + "\n";
        }
        return returnString;
    }
}
