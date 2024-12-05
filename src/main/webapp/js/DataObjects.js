
var leagues = [
  
  {
    "name": "Select A League",
    "code": "NA"
  },
  {
    "name": "Women's Flat Track Derby Association",
    "code": "WFTDA"
  },
  {
    "name": "Junior Roller Derby Association ",
    "code": "JR"
  },
  {
    "name": "Men's Roller Derby Association ",
    "code": "MRDA"
  },
  {
    "name": "Modern Athletic Derby Endeavor",
    "code": "MADE"
  },
  {
    "name": "Old School Derby Association",
    "code": "OSDA"
  },
  {
    "name": "Renegade Rollergirls",
    "code": "RR"
  },
  {
    "name": "Roller Derby Coalition of Leagues ",
    "code": "RCDoL"
  },
  {
    "name": "USA Roller Sports ",
    "code": "USA"
  },
  {
    "name": "World Organization of Roller Derby",
    "code": "World"
  },
  {
    "name": "Skate Australia ",
    "code": "AUS"
  },
  {
    "name": "Roller Sports Canada ",
    "code": "CAN"
  },
  {
    "name": "Norges Skøyteforbund  ",
    "code": "NOR"
  },
  {
    "name": "Svenska Skridskoförbundet ",
    "code": "SWE"
  },
  {
    "name": "British Roller Sports Federation  ",
    "code": "BRSF"
  },
  {
    "name": "<Not Listed> ",
    "code": "NL"
  }]

  
var states = [
  
  {
    "name": "Select A State",
    "code": "NA"
  },
  {
    "name": "Alabama",
    "code": "AL"
  },
  {
    "name": "Alaska",
    "code": "AK"
  },
  {
    "name": "Arizona",
    "code": "AZ"
  },
  {
    "name": "Arkansas",
    "code": "AR"
  },
  {
    "name": "California",
    "code": "CA"
  },
  {
    "name": "Colorado",
    "code": "CO"
  },
  {
    "name": "Connecticut",
    "code": "CT"
  },
  {
    "name": "Delaware",
    "code": "DE"
  },
  {
    "name": "Florida",
    "code": "FL"
  },
  {
    "name": "Georgia",
    "code": "GA"
  },
  {
    "name": "Hawaii",
    "code": "HI"
  },
  {
    "name": "Idaho",
    "code": "ID"
  },
  {
    "name": "Illinois",
    "code": "IL"
  },
  {
    "name": "Indiana",
    "code": "IN"
  },
  {
    "name": "Iowa",
    "code": "IA"
  },
  {
    "name": "Kansas",
    "code": "KS"
  },
  {
    "name": "Kentucky",
    "code": "KY"
  },
  {
    "name": "Louisiana",
    "code": "LA"
  },
  {
    "name": "Maine",
    "code": "ME"
  },
  {
    "name": "Maryland",
    "code": "MD"
  },
  {
    "name": "Massachusetts",
    "code": "MA"
  },
  {
    "name": "Michigan",
    "code": "MI"
  },
  {
    "name": "Minnesota",
    "code": "MN"
  },
  {
    "name": "Mississippi",
    "code": "MS"
  },
  {
    "name": "Missouri",
    "code": "MO"
  },
  {
    "name": "Montana",
    "code": "MT"
  },
  {
    "name": "Nebraska",
    "code": "NE"
  },
  {
    "name": "Nevada",
    "code": "NV"
  },
  {
    "name": "New Hampshire",
    "code": "NH"
  },
  {
    "name": "New Jersey",
    "code": "NJ"
  },
  {
    "name": "New Mexico",
    "code": "NM"
  },
  {
    "name": "New York",
    "code": "NY"
  },
  {
    "name": "North Carolina",
    "code": "NC"
  },
  {
    "name": "North Dakota",
    "code": "ND"
  },
  {
    "name": "Ohio",
    "code": "OH"
  },
  {
    "name": "Oklahoma",
    "code": "OK"
  },
  {
    "name": "Oregon",
    "code": "OR"
  },
  {
    "name": "Pennsylvania",
    "code": "PA"
  },
  {
    "name": "Rhode Island",
    "code": "RI"
  },
  {
    "name": "South Carolina",
    "code": "SC"
  },
  {
    "name": "South Dakota",
    "code": "SD"
  },
  {
    "name": "Tennessee",
    "code": "TN"
  },
  {
    "name": "Texas",
    "code": "TX"
  },
  {
    "name": "Utah",
    "code": "UT"
  },
  {
    "name": "Vermont",
    "code": "VT"
  },
  {
    "name": "Virginia",
    "code": "VA"
  },
  {
    "name": "Washington",
    "code": "WA"
  },
  {
    "name": "West Virginia",
    "code": "WV"
  },
  {
    "name": "Wisconsin",
    "code": "WI"
  },
  {
    "name": "Wyoming",
    "code": "WY"
  }
]

var position = [
  {
      "name": "Select A Position",
      "code": "NA"
    },

  {
    "name": "Jammer",
    "code": "JM"
  },
  {
    "name": "Jammer/Blocker",
    "code": "JB"
  },
  {
    "name": "Blocker",
    "code": "Bl"
  },
  {
      "name": "Pivot",
      "code": "Pi"
    },
    {
      "name": "Coach",
      "code": "Bl"
    }
    ,  {
      "name": "Skating Official",
      "code": "SO"
    },
    {
      "name": "Non-Skating Official",
      "code": "NSO"
    }
]


var GearSize = [
  {
      "name": "<Select A Size>",
      "code": "NA"
    },

  {
    "name": "Extra-Large",
    "code": "XL"
  },
  {
    "name": "Large",
    "code": "L"
  },
  {
      "name": "Medium",
      "code": "M"
    },
    {
      "name": "Small",
      "code": "S"
    }
    ,  {
      "name": "Extra-Small",
      "code": "XS"
    }]

    var pronouns = [
      {
        "name": "Please Select",
        "code": "NA"
      },
      {
          "name": "He/Him",
          "code": "M"
        },
    
      {
        "name": "She/Her",
        "code": "F"
      },
      {
        "name": "They/Them",
        "code": "NB"
      },
      {
          "name": "Other",
          "code": "O"
        },
        {
          "name": "Prefer Not To Say",
          "code": "P"
        }
        ]

    