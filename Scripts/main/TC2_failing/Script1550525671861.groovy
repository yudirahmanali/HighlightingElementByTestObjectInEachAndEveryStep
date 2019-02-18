import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import groovy.json.JsonOutput
import internal.GlobalVariable as GlobalVariable

String prettyPrint(Object obj) {
	String json = JsonOutput.toJson(obj)
	return JsonOutput.prettyPrint(json)
}

// open browser and navigate to the AUT
WebUI.openBrowser('')
WebUI.setViewPortSize(1024, 768)
WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')
WebUI.verifyElementPresent(findTestObject('Page_CURA Healthcare Service_top/a_Make Appointment'),
	10, FailureHandling.STOP_ON_FAILURE)

// highlight a specific element
CustomKeywords.'com.kazurayam.ksbackyard.HighlightElement.on'(
	findTestObject('Page_CURA Healthcare Service_top/h1_CURA Healthcare Service'))
WebUI.delay(1)


// modify WebUI.* keywords which take TestObject as args[0]
// so that they get highlighted automatically
CustomKeywords.'com.kazurayam.ksbackyard.HighlightElement.pandemic'()

// following verification will fail. This is intentional. Just to see what happens when a built-in keyword failed.
def verificationResult = WebUI.verifyElementAttributeValue(
							findTestObject('Page_CURA Healthcare Service_top/a_Make Appointment'),
							'href',
							'https://www.google.com/',
							5,
							FailureHandling.CONTINUE_ON_FAILURE)
if (!verificationResult) {
	println ">>> GlobalVariable.tcExceptionEvents: \n" + prettyPrint(GlobalVariable.tcExceptionEvents)
}
WebUI.delay(3)

WebUI.closeBrowser()