package javafxbead;

public class Filter {
    public String getPcPlace() {
        return pcPlace;
    }

    public Filter() {
    }

    public void setPcPlace(String pcPlace) {
        this.pcPlace = pcPlace;
    }

    public Filter(String pcPlace, String softName, String pcType, boolean toolsCheck, boolean hangCheck, boolean pluginCheck, boolean fajlCheck) {
        this.pcPlace = pcPlace;
        this.softName = softName;
        this.pcType = pcType;
        this.toolsCheck = toolsCheck;
        this.hangCheck = hangCheck;
        this.pluginCheck = pluginCheck;
        this.fajlCheck = fajlCheck;
    }

    public String getSoftName() {
        return softName;
    }

    public void setSoftName(String softName) {
        this.softName = softName;
    }

    public String getPcType() {
        return pcType;
    }

    public void setPcType(String pcType) {
        this.pcType = pcType;
    }

    public boolean isToolsCheck() {
        return toolsCheck;
    }

    public void setToolsCheck(boolean toolsCheck) {
        this.toolsCheck = toolsCheck;
    }

    public boolean isHangCheck() {
        return hangCheck;
    }

    public void setHangCheck(boolean hangCheck) {
        this.hangCheck = hangCheck;
    }

    public boolean isPluginCheck() {
        return pluginCheck;
    }

    public void setPluginCheck(boolean pluginCheck) {
        this.pluginCheck = pluginCheck;
    }

    public boolean isFajlCheck() {
        return fajlCheck;
    }

    public void setFajlCheck(boolean fajlCheck) {
        this.fajlCheck = fajlCheck;
    }

    private String pcPlace;
    private String softName;
    private String pcType;

    private boolean toolsCheck;
    private boolean hangCheck;
    private boolean pluginCheck;
    private boolean fajlCheck;
}
