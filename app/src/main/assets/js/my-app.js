// Initialize your app
var myApp = new Framework7({
    animateNavBackIcon:true,
swipePanel: 'left',
 		precompileTemplates: true
});

// Export selectors engine
var $$ = Dom7;

// Add main View
var mainView = myApp.addView('.view-main', {
    // Enable dynamic Navbar
    dynamicNavbar: true,
    // Enable Dom Cache so we can use all inline pages
    domCache: true
});
//myApp.alert("Start tracing...");
//var count=data.length;


//myApp.alert("length "+data.length);
function hello(msg){
	
	myApp.alert(msg,"Greeting");
}

// call from android
function back_to_main(){
	//myApp.alert("back_to_main");
	mainView.router.back();
}
//hello("hello world");

var myList = myApp.virtualList('.list-block.virtual-list.cards-list', {
items : data,
//rowsBefore	:1900,
renderItem: function (index, item) {
	return 	'<li class="card wordcard" onclick="showdata('+index+')">'+
	'<a href="#defination" class="item-link item-content"'+
     ' <div class="card-content">'+
       ' <div class="card-content-inner engdef">'+item.endef+'</div>'+
     ' </div>'+
     ' <div class="card-footer mmdef">'+item.mmdef+'</div>'+
	'</a>'+
'  </li>';},
    // search all items, we need to return array with indexes of matched items
    // search item by item
    searchByItem: function (query, index, item) {
        // Check if title contains query string
        if (item.endef.indexOf(query.trim()) >= 0 || item.mmdef.indexOf(query.trim()) >= 0) {
            return true; //item matches query
        }
        else {
            return false; //item doesn't match
        }
    }
});            

// Search bar
var mySearchbar = myApp.searchbar('.searchbar', {
    searchList: '.list-block.virtual-list.cards-list'
    //searchIn: '.card-content-inner, .card-footer'
});   


function showdata(index){
//myApp.alert("show data function");

//myApp.alert(index+ " is index");
var eng=data[index].endef;
var mm=data[index].mmdef;
//myApp.alert("text is : "+eng+mm);
//var eng=$("div.engdef", this).text();
	//var mm=$("div.mmdef", this).text();
	//myApp.alert(eng+'<br>'+mm,"Defination");
//myApp.alert(eng+"<br><hr>"+mm,"Defination");
addDefCard(eng,mm);
$("#btnSayIt").click(function(){
    try{
        Android.speak(eng);
    } catch(error){
        myApp.alert("Speak feature is available on only Android","Warning");
    }
    
});
//Android.speak(eng);
}

/*
$("li.wordcard").click(function(){
	//myApp.alert("clicked on item");
	var eng=$("div.engdef", this).text();
	var mm=$("div.mmdef", this).text();
	myApp.alert(eng+'<br>'+mm,"Defination");
//myApp.alert("clicked on "+eng+"="+mm);
addDefCard(eng,mm);
});
*/

function addDefCard(eng,mm){
var defHTML = myApp.templates.defTemplate({
    engTemp:eng,
		mmTemp:mm
});

$("#defcard").empty();
$("#defcard").prepend(defHTML);

}

//myApp.alert("Welcome to u");

/*
var defHTML = Template7.templates.defTemplate({
    engTemp:"",
		mmTemp:""
});
myApp.alert(defHTML);


dataarray[dataarray.length]=    {
           endef:"loveudo",
							mmdef:"​ခ်စ္​သူ"
        };
var dataobj={};
dataobj.endef="endef";
dataobj.mmdef="အခ်စ္​";
dataarray[dataarray.length]=   dataobj;

for(var i=0;i<1;i++){
var dataobj={};
dataobj.endef="endef"+i;
dataobj.mmdef="အခ်စ္​";
dataarray[dataarray.length]=   dataobj;

}
*/

