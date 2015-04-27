metadata {
    definition (name: "My Home", namespace: "davglass", author: "Dav Glass") {
        command "countWindows", ["number", "number"]
        command "setTemp", ["number"]
        command "setMotion", ["number", "number"]
        command "setPower", ["number"]
        command "setDoors", ["number", "number"]
        command "setLocks", ["number", "number"]
        command "setLights", ["number", "number"]
        
        attribute "value", "string"
    }
    tiles {
        standardTile("windows", "device.contact", width: 1, height: 1) {
            state "open", label: '${name}', icon: "st.Home.home9-icn", backgroundColor: "#ffa81e"
            state "closed", label: '${name}', icon: "st.Home.home9-icn", backgroundColor: "#79b821"
        }
        valueTile("windowsTile", "device.value", decoration: "flat") {
            state "items", label:'${currentValue}'
        }
        valueTile("temperature", "device.temperature") {
            state("temperature", label:'${currentValue}°', unit:"F",
                backgroundColors:[
                    [value: 31, color: "#153591"],
                    [value: 44, color: "#1e9cbb"],
                    [value: 59, color: "#90d2a7"],
                    [value: 74, color: "#44b621"],
                    [value: 84, color: "#f1d801"],
                    [value: 95, color: "#d04e00"],
                    [value: 96, color: "#bc2323"]
                ]
            )
        }
        standardTile("motion", "device.motion") {
            state "active", label:'motion', icon:"st.motion.motion.active", backgroundColor:"#53a7c0"
            state "inactive", label:'no motion', icon:"st.motion.motion.inactive", backgroundColor:"#ffffff"
        }
        valueTile("motionTile", "device.motionCount", decoration: "flat") {
            state "items", label:'${currentValue}'
        }
        valueTile("powerDisp", "device.power") {
            state (
                "default", 
                label:'${currentValue}', 
                foregroundColors:[
                    [value: 1, color: "#000000"],
                    [value: 10000, color: "#ffffff"]
                ], 
                foregroundColor: "#000000",
                backgroundColors:[
                    [value: "0 Watts",      color: "#153591"],
                    [value: "500 Watts",   color: "#1e9cbb"],
                    [value: "1000 Watts",   color: "#90d2a7"],
                    [value: "1500 Watts",   color: "#44b621"],
                    [value: "2000 Watts",  color: "#f1d801"],
                    [value: "2500 Watts",  color: "#d04e00"], 
                    [value: "3000 Watts",  color: "#bc2323"] 
                ]
            )
        }
        standardTile("doors", "device.doors") {
            state "open", label:'open', icon:"st.contact.contact.open", backgroundColor:"#53a7c0"
            state "closed", label:'closed', icon:"st.contact.contact.closed", backgroundColor:"#79b821"
        }
        valueTile("doorsTile", "device.doorsCount", decoration: "flat") {
            state "items", label:'${currentValue}'
        }
        standardTile("locks", "device.locks") {
            state "locked", label:'locked', icon:"st.locks.lock.locked", backgroundColor:"#79b821"
            state "unlocked", label:'unlocked', icon:"st.locks.lock.unlocked", backgroundColor:"#FF0000"
        }
        valueTile("locksTile", "device.locksCount", decoration: "flat") {
            state "items", label:'${currentValue}'
        }
        standardTile("lights", "device.lights", width: 1, height: 1) {
            state "on", label: '${name}', icon: "st.Lighting.light13", backgroundColor: "#79b821"
            state "off", label: '${name}', icon: "st.Lighting.light13", backgroundColor: "#ffffff"
        }
        valueTile("lightsTile", "device.lightsCount", decoration: "flat") {
            state "items", label:'${currentValue}'
        }
        
        
        valueTile("blank1", "device.blank1", decoration: "flat") {
            state "items", label:''
        }
        valueTile("blank2", "device.blank1", decoration: "flat") {
            state "items", label:''
        }
        valueTile("main", "device.main") {
            state "default", label: "glance", backgroundColor:"#79b821", icon:"st.Home.home2"
        }

        main "main"
        details([
            "windows", "motion", "doors", 
            "windowsTile", "motionTile", "doorsTile",
            "locks", "lights", "temperature", 
            "locksTile", "lightsTile", "powerDisp"
        ])
    }
}

def parse(String description) { }

def countWindows(open, closed) {
    def status = ((open == 0) ? "closed" : "open");
    log.debug "Open: ${open}, Closed: ${closed}, Status: ${status}"
    sendEvent(name: "contact", value: status)
    sendEvent(name: "value", value: "${open}/${closed}")   
}

def setDoors(open, closed) {
    def status = ((open == 0) ? "closed" : "open");
    log.debug "Open: ${open}, Closed: ${closed}, Status: ${status}"
    sendEvent(name: "doors", value: status)
    sendEvent(name: "doorsCount", value: "${open}/${closed}")  
}

def setLocks(open, closed) {
    def status = ((open == 0) ? "locked" : "unlocked");
    log.debug "Open: ${open}, Closed: ${closed}, Status: ${status}"
    sendEvent(name: "locks", value: status)
    sendEvent(name: "locksCount", value: "${open}/${closed}")  
}

def setLights(open, closed) {
    def status = ((open == 0) ? "off" : "on");
    log.debug "Open: ${open}, Closed: ${closed}, Status: ${status}"
    sendEvent(name: "lights", value: status)
    sendEvent(name: "lightsCount", value: "${open}/${closed}")  
}
def setTemp(temp) {
    sendEvent(name: "temperature", value: temp);
}

def setMotion(active, inactive) {
    def status = ((active == 0) ? "inactive" : "active");
    sendEvent(name: "motion", value: status);
    def msg = "${active}/${inactive}";
    sendEvent(name: "motionCount", value: msg);
}

def setPower(power) {
    sendEvent(name: "power", value: power);
}

