def DeleteParty(){
def partyId = context.partyId
    def party = ec.entity.find("Nikunj.party.Party").condition("partyId" , partyId).one()

    if(!party){
        ec.message.addError("The given PartyId do not exists")
        return
    }

    if(party.partyTypeEnumId=="PERSON"){
         // two condition the current partyId can be exist in the person or do not exist
        // if exist then delete else do nothing
        def person = ec.entity.find("Nikunj.party.Person").condition("partyId" , partyId).one()
        if(person){
            person.delete()
        }
    }
    else {
        // two condition the current partyId can be exist in the PartyGroup or do not exist
        // // if exist then delete else do nothing
        def partyGroup = ec.entity.find("Nikunj.party.PartyGroup").condition("partyId",partyId).one()
        if(partyGroup) {
          partyGroup.delete()
        }
    }
    party.delete()

    return context.message="party deleted successfully"
}

def DeletePerson(){
def partyId = context.partyId
    def person = ec.entity.find("Nikunj.party.Person").condition("partyId",partyId).one()

    if(!person){
        ec.message.addError("The entered partyId does not exist in the Person-table")
        return
    }

    person.delete()
    return context.message = "Person delete successfully"
}

def DeletePartyGroup() {
    def partyId = context.partyId
    def partyGroup = ec.entity.find("Nikunj.party.PartyGroup").condition("partyId", partyId).one()

    if (!partyGroup) {
        ec.message.addError("The entered partyId does not exist in the Party-table")
        return
    }
    partyGroup.delete()
    return context.message = "Party deleted successfully"
}

