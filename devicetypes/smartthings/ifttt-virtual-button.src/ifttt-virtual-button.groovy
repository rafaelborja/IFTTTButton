/**
 * Copyright 2017 Rafael Borja 
 * 
 * Device handler to simulate button behavior from a IFTTT Smartthings Switch. This devicetype is usefull, to trigger events every time an IFTTT rules switches a swith ON.
 * Using a regular switch, the the switch would be always ON after an IFTTT event. This device turn's button off and create a button push event to be used with Automations (new App)
 *
 * Based on https://github.com/SmartThingsCommunity/SmartThingsPublic/tree/master/devicetypes/smartthings/virtual-switch.src
 *
 /**
 *  Momentary Button Tile
 *
 *  Author: SmartThings
 *
 *  Date: 2013-05-01
 */
metadata {
	definition (name: "IFTTT Virtual Button", namespace: "smartthings", author: "Rafael Borja") {
		capability "Actuator"
		capability "Switch"
		capability "Momentary"
		capability "Sensor"
	}

	// simulator metadata
	simulator {
	}

	// UI tile definitions
	tiles {
		standardTile("switch", "device.switch", width: 2, height: 2, canChangeIcon: true) {
			state "off", label: 'Push', action: "momentary.push", backgroundColor: "#ffffff", nextState: "on"
			state "on", label: 'Push', action: "momentary.push", backgroundColor: "#53a7c0"
		}
		main "switch"
		details "switch"
	}
}

def parse(String description) {
	log.debug "PARSE ${description}"
}

def push() {
	log.debug "IFTTT Virual Button PUSH"
	sendEvent(name: "switch", value: "on", isStateChange: true, display: false)
	sendEvent(name: "switch", value: "off", isStateChange: true, display: false)
	sendEvent(name: "momentary", value: "pushed", isStateChange: true)
}

def on() {
	log.debug "IFTTT Virual Button ON"
	push()
}

def off() {
	log.debug "IFTTT Virual Button OFF"
	push()
}