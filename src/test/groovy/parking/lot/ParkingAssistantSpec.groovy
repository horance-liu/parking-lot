package parking.lot

import spock.lang.Specification

class ParkingAssistantSpec extends Specification {
  def "assistant park the car, then take the car"() {
    given:
    def parkingLot = new ParkingLot(1)
    def assistant = new ParkingAssistant()
    assistant.add(parkingLot)

    def car = new Car("A2TX82")

    when:
    def card = assistant.park(car)

    then:
    assistant.take(card) == car
  }

  def "park the car into parking lot"() {
    given:
    def parkingLot = new ParkingLot(1)
    def assistant = new ParkingAssistant()
    assistant.add(parkingLot)

    def car = new Car("A2TX82")

    when:
    def card = assistant.park(car)

    then:
    assistant.take(card) == car
  }

  def "parking lot will be overflow"() {
    when:
    def parkingLot = new ParkingLot(0)
    def assistant = new ParkingAssistant()
    assistant.add(parkingLot)

    then:
    assistant.isFree() == false
  }

  def "can't take the car by invalid parking card"() {
    given:
    def parkingLot = new ParkingLot(1)
    def assistant = new ParkingAssistant()
    assistant.add(parkingLot)

    when:
    assistant.park(new Car("A2TX82"))

    then:
    def otherParkingCard = new ParkingCard("A66666");
    assistant.exist(otherParkingCard) == false
  }

  def "parking assistant manage multiple parking lots"() {
    given:
    def parking1 = new ParkingLot(1)
    def parking2 = new ParkingLot(1)

    def assistant = new ParkingAssistant()
    assistant.add(parking1)
    assistant.add(parking2)

    def car1 = new Car("A2TX82")
    def car2 = new Car("A66666")

    when:
    def card1 = assistant.park(car1)
    def card2 = assistant.park(car2)

    then:
    assistant.take(card1) == car1
    assistant.take(card2) == car2
  }

  def "max vacancy num"() {
    given:
    def parking1 = new ParkingLot(1)
    def parking2 = new ParkingLot(3)

    def assistant = new ParkingAssistant(ParkingSelectors.maxVacancyNum())
    assistant.add(parking1)
    assistant.add(parking2)

    def car = new Car("A2TX82")

    when:
    def card = assistant.park(car)

    then:
    parking1.getVacancyNum() == 1
    parking2.getVacancyNum() == 2
  }

  def "max vacancy rate"() {
    given:
    def parking1 = new ParkingLot(20)
    def parking2 = new ParkingLot(10)

    def assistant = new ParkingAssistant(ParkingSelectors.maxVacancyNum())
    assistant.add(parking1)
    assistant.add(parking2)

    when:
    assistant.park(new Car("A2TX82"))
    assistant.park(new Car("A66666"))

    then:
    parking1.getVacancyNum() == 18
  }

  def "text printer"() {
    given:
    def parking1 = new ParkingLot(20)
    def parking2 = new ParkingLot(10)

    def assistant = new ParkingAssistant(ParkingSelectors.maxVacancyNum())
    assistant.add(parking1)
    assistant.add(parking2)

    when:
    assistant.park(new Car("A2TX82"))
    assistant.park(new Car("A66666"))

    then:
    TextPrinter printer = new TextPrinter(assistant)
    System.out.println(printer.toString())
    parking1.getVacancyNum() == 18
  }
}
