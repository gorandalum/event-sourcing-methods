options({
    resultStreamName: "eventstore_example_projection",
    $includeLinks: false,
    reorderEvents: false,
    processingLag: 0
})

fromStream('eventstore_example')
    .when({
        $init:function(){
            return {
                customers: []
            }
        },
        CustomerCreatedEvent: function(state, event){
            state.customers.push({
                ssn: event.data.ssn,
                name: event.data.name,
                email: event.data.email,
                accounts: []
            });
        },
        AccountCreatedEvent: function(state, event){
            state.customers
                .find(c => c.ssn === event.data.customerSsn)
                .accounts
                .push({
                    number: event.data.number,
                    balance: 0
                })
        },
        ChangedEmailEvent: function(state, event){
            state.customers
                .find(c => c.ssn === event.data.customerSsn)
                .email = event.data.email
        },
        TransactionRegisteredEvent: function(state, event){
            state.customers
                .find(c => c.ssn === event.data.customerSsn)
                .accounts
                .find(a => a.number === event.data.accountNumber)
                .balance += event.data.amount
        }
    })
    .outputState()