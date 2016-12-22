import $ivy.`com.github.agourlay::cornichon:0.10.4`

import com.github.agourlay.cornichon.CornichonFeature

new CornichonFeature {

  override lazy val baseUrl = "http://localhost:8080"

  def feature =
    Feature("Contacts API") {

      Scenario("search for non-existing contact") {
        When I get("/contacts").withParams("name" -> data.nonExistingName)
        Then assert status.is(200)
        And assert body.is("[]")
      }

      Scenario("create new contact") {
        Given I post("/contacts").withBody(data.request1)
        When I get("/contacts").withParams("name" -> data.name)
        Then assert status.is(200)
        And assert body.path("[0].name").is(data.name)
        And assert body.path("[0].lastName").is(data.lastName)
        And assert body.path("[0].phones").asArray.contains(data.phone1)
      }

      Scenario("add new phone to existing contact") {
        Given I post("/contacts").withBody(data.request2)
        When I get("/contacts").withParams("name" -> data.name)
        Then assert status.is(200)
        And assert body.path("[0].name").is(data.name)
        And assert body.path("[0].lastName").is(data.lastName)
        And assert body.path("[0].phones").asArray.contains(data.phone1)
        And assert body.path("[0].phones").asArray.contains(data.phone2)
      }

    }

  object data {
    val name = "Bruce"
    val lastName = "Wayne"
    val nonExistingName = "Batman"
    val phone1 = "+ 90 500 000 00 00"
    val phone2 = "+ 90 500 000 00 01"

    val request1 = request(phone1)
    val request2 = request(phone2)

    def request(phone: String): String =
      s"""
        |[{
        |  "name": "$name",
        |  "lastName": "$lastName",
        |  "phone": "$phone"
        |}]
      """.stripMargin.trim

  }

}.execute()