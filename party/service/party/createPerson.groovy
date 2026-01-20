// Now what we want let write it down , as per assignment
//  Create Person with firstName require , lastName require , and DOB is optional and only create when partyId is already in the Party table

// fetch  current partyId coming from  api request is present in Party table or not


// fetch current partyId coming from api request
def partyId = context.partyId

// check if Party exists
def party = ec.entity.find("Nikunj.party.Party").condition("partyId", partyId).one()

if (!party) {
    ec.message.addError("Party yet to be created with partyId ${partyId}")
    return
}

if(party.partyTypeEnumId !="PERSON"){
    ec.message.addError("Party ${partyId} exists but is of type ${party.partyTypeEnumId}. Person can be created only for PERSON type.")
    return

}


// create Person only if Party exists
def person = ec.entity.makeValue("Nikunj.party.Person")

person.partyId = partyId
person.firstName = context.firstName
person.lastName = context.lastName
if (context.dateOfBirth) {
    person.dateOfBirth = context.dateOfBirth
}
person.create()

context.message = "Person ${person.firstName} ${person.lastName} created successfully!"



