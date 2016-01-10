//  
function selectComponent(event) {
    var comp = event.getSource();
    AdfCustomEvent.queue(comp, 'selectComponentServer', 
    {
    },
true);
}

function selectNode(event) {
    var comp = event.getSource();
    var checkValue = comp.getValue();
    AdfCustomEvent.queue(comp, 'serverSelectNode', 
    {
        'checkValue' : checkValue
    },
true);
}

function selectRoleLib(event) {
    var comp = event.getSource();
    AdfCustomEvent.queue(comp, 'selectRoleLibServer', 
    {
    },
true);
}

function selectAllRes(event) {
    var comp = event.getSource();
    AdfCustomEvent.queue(comp, 'selectAllResServer', 
    {
    },
true);
}

function clickSelectAllCheckBox(evt) {
    var selectAllCheckBox = evt.getSource();
    var isChecked = selectAllCheckBox.getValue();
    AdfCustomEvent.queue(selectAllCheckBox, "SelectAllCheckBoxClickEvent", 
    {
        params : isChecked
    },
false);
}

function clickSelectOneCheckBox(evt) {
    var selectCheckBox = evt.getSource();
    var isChecked = selectCheckBox.getValue();
    var docId = selectCheckBox.getProperty('docId');
    AdfCustomEvent.queue(selectCheckBox, "SelectCheckBoxClickEvent", 
    {
        params : isChecked, docId : docId
    },
false);
}