package com.company.selenium

import org.openqa.selenium.{WebElement, By}
import org.openqa.selenium.firefox.FirefoxDriver

/**
 * taken from post by Pavol Vaskovic on newsgroup scala-user@listes.epfl.ch
 */
object BrowserSpeak {
  
  def startBrowser = {driver = new FirefoxDriver}

  def closeBrowser {driver.close}

  var driver: FirefoxDriver = null

  implicit def toRichWebElement(we: WebElement): RichWebElement = new RichWebElement(we)

  class RichWebElement(element: WebElement) {
    def enter(text: String) = element.sendKeys(text)
  }

  def go = driver.navigate

  def form = driver.findElement(By.tagName("form"))

  object page {
    def title = driver.getTitle
    def count(what: By) = driver.findElements(what).size
    def textAt(xpath :String) = driver.findElementByXPath(xpath).getText
  }

  object className {
    def apply(className: String): By = By.className(className)
  }

  object xpath {
    def apply(exp: String): By = By.xpath(exp)
  }

  object to {
    def field(id: String) = driver.findElement(By.id(id))
  }

  object click {
    def link(linkText: String) = driver.findElement(By.linkText(linkText)).click
    def button(id :String) = driver.findElement(By.id(id)).click
  }
}