package src.hack;

public class Hack{
    int hackID;
    String hackName;
    String hackStartDate;
    String hackEndDate;
    String hackMode;
    String hackLocation;
    boolean womenHack;
    String hackReg;
    String hackLink;

    public Hack(int hackID, String hackName, String hackStartDate, String hackEndDate, String hackMode, String hackLocation, boolean womenHack, String hackReg, String hackLink) {
        this.hackID = hackID;
        this.hackName = hackName;
        this.hackStartDate = hackStartDate;
        this.hackEndDate = hackEndDate;
        this.hackMode = hackMode;
        this.hackLocation = hackLocation;
        this.womenHack = womenHack;
        this.hackReg = hackReg;
        this.hackLink = hackLink;
    }

    public int getHackID() {
        return this.hackID;
    }

    public String getHackName() {
        return this.hackName;
    }

    public String getHackStartDate() {
        return this.hackStartDate;
    }

    public String getHackEndDate() {
        return this.hackEndDate;
    }


    public String getHackMode() {
        return this.hackMode;
    }

    public String getHackLocation() {
        return this.hackLocation;
    }

    public boolean getWomenHack() {
        return this.womenHack;
    }

    public String getHackReg() {
        return this.hackReg;
    }

    public String getHackLink() {
        return this.hackLink;
    }

    public String printString() {
        String returnString = "";
        if (getHackEndDate() != null) {
            returnString = getHackName() + "\nDate: " + getHackStartDate() + "-" + getHackEndDate() + "\nMode: " + getHackMode() + "\nLocation: " + getHackLocation() + "\nRegistration: " + getHackReg() + "\nLink: " + getHackLink() + "\n";
        }
        else {
            returnString = getHackName() + "\nDate: " + getHackStartDate() + "\nMode: " + getHackMode() + "\nLocation: " + getHackLocation() + "\nRegistration: " + getHackReg() + "\nLink: " + getHackLink() + "\n";
        }
        return returnString;
    }
}