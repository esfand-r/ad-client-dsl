This is just about trying to find out if it is easy and would make sense to have a really simple but useful mini dsl.

For now its possible to do something like 

```
        val connect = new AppDirectConnection connect att using Oauth1(clientId = "", clientSecret = "")
    
        connect to listing getBy listingQuery(appType = AppType.WEB_APP, order = SortOrder.ALPHABETICAL, searchString = "test") execute synchronously
    
        connect to listing getBy listingQuery(appType = AppType.WEB_APP, order = SortOrder.ALPHABETICAL) execute synchronously
    
        connect to listing getBy listingQuery(appType = AppType.WEB_APP, order = SortOrder.ALPHABETICAL) execute synchronously
    
        connect to bundle get all execute synchronously
    
        connect to listing get (10 apps) execute asynchronously onComplete {
          case Success(response) => println("Response" + response.getResponseBody)
          case Failure(error) => println("An error has occurred: " + error.getMessage)
        }
    
        connect to company get "74c01d4a-6944-4d40-afd5-439b612a6f41" execute synchronously
    
        connect to companyUser(companyId = "1") get all execute synchronously
    
        val address = Address(city = "Montreal", country = "Canada", state = "QC", street1 = "1400 Notre Dame", zip = "12345")
        val contact = Contact(phoneNumber = "5147956401") withAddress address
    
        connect to company create (Company(id = "12333dd33c3", name = "ad5") withContact (contact withAddress address)) execute synchronously
    
        connect to company delete "74c01d4a-6944-4d40-afd5-439b612a6f41" execute synchronously
    
        connect to company update "74c01d4a-6944-4d40-afd5-439b612a6f41" using (Company(id = "12333dd33c3", name = "ad5") withContact contact) execute synchronously
    
        val body = connect to company get all execute synchronously getResponseBody
    
        println(body)                
```

