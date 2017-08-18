
//to get all topics on home page
function getAllTopics() {
	var topicsUrl = "/rest/topic/getAllTopics";
	//$('.ajax-loader').css("visibility", "visible");

	$.ajax({
		type: "GET",
		url: topicsUrl,
		//dataType: "text",
		success: function(data2) {
			//alert(data);
			//if(data.status == "success"){
			//if(data != null){
			//alert(data2);
			var obj = JSON.parse(data2);
			//alert(JSON.stringify(obj));
			//alert(obj.status);
			if(obj.status=="success")
			{
				var data = obj.topicList;
				//alert(JSON.stringify(data));
				//var topicListSize = data.length;
				var topicListSize = Object.keys(data).length;
				//alert(topicListSize);
				for (var i = topicListSize-1; i >= 0; i--) {//todo i++ - figure out how to do
					
					/*if(i>=topicListSize-2){
						continue;
					}*/
					
					var id;
					var title;
					var description;
					var createdAt;
					var createdBy;
					var upVoteCount;
					var downVoteCount;
					var isVoted;
					var voteValue;
					var tags;
					var topicUrl;
					var topicId;
					var commentList=[];

					var j = 0;
					var topics = [];
					var topicI="topic-"+i;
					topics.push("<div id='"+topicI+"' class='row well well-lg marginTopics'>");
					$.each( data[i] , function( key, val ) {
						if(key.toString()=="id"){
							id=val;
						} else if(key.toString()=="title"){
							title=val;
						} else if(key.toString()=="description"){
							description=val;
						} else if(key.toString()=="createdAt"){
							createdAt=val;
						} else if(key.toString()=="createdBy"){
							createdBy=val;
						} else if(key.toString()=="upVoteCount"){
							upVoteCount=val;
						} else if(key.toString()=="downVoteCount"){
							downVoteCount=val;
						} else if(key.toString()=="isVoted"){
							isVoted=val;
						} else if(key.toString()=="voteValue"){
							voteValue=val;
						} else if(key.toString()=="tags"){
							tags=val;
						} else if(key.toString()=="topicUrl"){
							topicUrl=val;
						} else if(key.toString()=="commentList"){
							commentList=val;
						} else if(key.toString()=="topicId"){
							topicId=val;
						}
						j++;
					});

					/* var myDate = new Date('2011-07-14 11:23:00');
					alert("full-year: "+myDate.getFullYear())
					alert("day: "+myDate.getDay())
					alert("date: "+myDate.getDate()) */

					var upvoteBtnVar="upvote-btn-"+topicI;
					var downvoteBtnVar="downvote-btn-"+topicI;

					var upvoteLblVar="upvote-lbl-"+topicI;
					var downvoteLblVar="downvote-lbl-"+topicI;

					var voteValue4Up=1;
					var voteValue4Down=2;

					var syncVotesFunc4Up = "syncVotes(\""+upvoteLblVar+"\",\""+downvoteLblVar+"\",\""+voteValue4Up+"\","+upVoteCount+","+downVoteCount+","+id+")";
					var syncVotesFunc4Down = "syncVotes(\""+upvoteLblVar+"\",\""+downvoteLblVar+"\",\""+voteValue4Down+"\","+upVoteCount+","+downVoteCount+","+id+")";

					topics.push( "<label class='row well strong lead col-md-12 large bg-faded text-primary marginContents' id='title-topic-"+id+"'>" + title + "</label>" );
					topics.push( "</br><label class='row well col-md-12 medium marginContents' id='description'><label class='small text-info'>Description:</label></br> " + description + "</label>" );
					topics.push( "</br><label class='small text-warning' id='"+upvoteLblVar+"'>"+ upVoteCount + " UpVotes</label>" );
					topics.push( "&nbsp&nbsp&nbsp&nbsp&nbsp<label class='small text-warning' id='"+downvoteLblVar+"'>"+ downVoteCount +" DownVotes</label>" );
					topics.push( "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<label class='small' id='createdBy'><label class='text-muted'>Created By: </label> "+ createdBy + "</label>" );
					topics.push( "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<label class='small' id='createdAt'><label class='text-muted'>Created At: </label> "+ formatToLocalDateStr(createdAt) + "</label>" );
					topics.push( "</br>&nbsp&nbsp<button class='btn btn-xs' id='"+upvoteBtnVar+"' name='"+upvoteBtnVar+"' onclick='"+syncVotesFunc4Up+"'><img class='upvote-img' id='upvote'></img></button>" );
					topics.push( "&nbsp&nbsp<button class='btn btn-xs' id='"+downvoteBtnVar+"' name='"+downvoteBtnVar+"' onclick='"+syncVotesFunc4Down+"')'><img class='downvote-img' id='downvote'></img></button>" );

					//validate up/down vote buttons
					validateUpDownBtn(upvoteBtnVar,downvoteBtnVar,isVoted,voteValue);

					var commentListVar="commentList-"+i;
					topics.push( "<div id='"+commentListVar+"' class='marginRow'>" );   //start commentList logic
					topics.push("</div>");
					$(topics.join( "" )).appendTo( "#topicList" );

					var commentListSize = commentList.length;
					for (var l = 0; l < commentListSize; l++) {
						var commentId;
						var commentType;
						var commentValue;
						var commentCreatedAt;
						var commentCreatedBy;
						var comments = [];
						var k=0;
						$.each( commentList[l] , function( key, val ) {
							if(key.toString()=="id"){
								commentId=val;
							} else if(key.toString()=="type"){
								commentType=val;
							} else if(key.toString()=="value"){
								commentValue=val;
							} else if(key.toString()=="createdAt"){
								commentCreatedAt=val;
							} else if(key.toString()=="createdBy"){
								commentCreatedBy=val;
							}
							k++;
						});

						//for existing comments
						comments.push( "<div class='row well marginRow'>");
						comments.push( "<label class='col-md-offset-0 col-md-1 small' id='commentCreatedBy'>" + commentCreatedBy + "</label>");
						comments.push( "&nbsp&nbsp&nbsp&nbsp<input class='small col-md-7 bg-success' readonly='true' id='commentValue' value='"+ commentValue + "'></input>" );
						comments.push( "<label class='small' id='commentCreatedAt'>"+ formatToLocalDateStr(commentCreatedAt) + "</label>" );
						comments.push( "</div>");

						$(comments.join( "" )).appendTo( "#"+commentListVar);
					}

					//for new comment
					var newCommentFieldId="add-comment-"+topicI+"-comment-"+commentListSize;
					commentListSize+=1;
					//var userName = document.getElementById('userName').value;
					var userName = $.urlParam("userName");
					var newComment=[];
					newComment.push( "<div class='row well marginRow'>");
					newComment.push( "<label class='col-md-offset-0 col-md-1 small' id='newCommentCreatedBy'>" + userName + "</label>");
					newComment.push( "<input type='text' class='small col-md-7' id='"+newCommentFieldId+"' name='"+newCommentFieldId+"' placeholder='add new comment'></input>");
					newComment.push( "&nbsp&nbsp&nbsp&nbsp<input type='button' value='Add' name='addComment' id='addComment' class='btn btn-info btn-xs' onclick='addNewComment(\""+newCommentFieldId+"\",\""+id+"\",\""+userName+"\")'></input>");
					//newComment.push( "&nbsp&nbsp&nbsp&nbsp<input type='button' value='Add' name='addComment' id='addComment' class='btn btn-info btn-xs' onclick='addNewComment()'></input>");
					//newComment.push( "<input type='button' value='Add Comment' name='newComment' id='newComment' class='btn btn-info btn-sm pull-right' onclick='"+onClickFunc+"'></input>");
					//newComment.push( "<input type='button' value='Add' name='addComment' id='addComment' class='btn btn-info btn-md pull-right' onclick='addNewComment()'></input>");
					newComment.push( "</div>");

					$(newComment.join( "" )).appendTo( "#"+commentListVar);
				}
				//});

			}
			//location.reload();
			/*}else{
			    alert("Some error, please try again!");
			}*/
		},
		error : function(jqXHR, textStatus, errorThrown) {
			var myJSON = JSON.stringify(jqXHR);
			//alert(myJSON);
			//alert("Connection error. Please try again.");

			//alert(JSON.stringify(this));
			//obj = JSON.parse(this);
			//alert(this);
		}
		/*, complete: function(){ $('.ajax-loader').css("visibility", "hidden"); }*/
	});
	/*var jsonData={ 	"status" : "success",
			"topicList" : [
			               {
			            	   "id":"1",
			            	   "title":"pune marathon in july 2017",
			            	   "description":"this topic is based on the the marathod happened in pune in july 2017. want to know reviews on the same.",
			            	   "createdAt":"2017-07-23 16:11:10",
			            	   "createdBy":"Swapnil Solanki",
			            	   "upVoteCount":"10",
			            	   "downVoteCount":"3",
			            	   "isVoted":"Y",
			            	   "voteValue":"1",
			            	   "tags":"sports",
			            	   "topicUrl":"testTopic1",
			            	   "topicId":"1",
			            	   "commentList" :[
			            	                   {
			            	                	   "id":"1",
			            	                	   "type":"T",
			            	                	   "value":"awesome marathon",
			            	                	   "createdAt":"2017-07-24 16:11:10",
			            	                	   "createdBy":"punjabi"
			            	                   },
			            	                   {
			            	                	   "id":"3",
			            	                	   "type":"T",
			            	                	   "value":"loved it!",
			            	                	   "createdAt":"2017-07-25 12:11:10",
			            	                	   "createdBy":"Ankit"
			            	                   }
			            	                   ]
			               } ,
			               {
			            	   "id":"2",
			            	   "title":"TEDx event in Mumbai",
			            	   "description":"i want to know more details about TEDx event happened in mumbai in April 2017",
			            	   "createdAt":"2017-07-21 16:11:10",
			            	   "createdBy":"Himanshu Singh",
			            	   "upVoteCount":"20",
			            	   "downVoteCount":"7",
			            	   "isVoted":"Y",
			            	   "voteValue":"2",
			            	   "tags":"general",
			            	   "topicUrl":"testTopic2",
			            	   "topicId":"2",
			            	   "commentList" :[
			            	                   {
			            	                	   "id":"2",
			            	                	   "type":"T",
			            	                	   "value":"it is nice one!",
			            	                	   "createdAt":"2017-07-22 16:11:10",
			            	                	   "createdBy":"balaji"
			            	                   }
			            	                   ]
			               },
			               {
			            	   "id":"3",
			            	   "title":"IEEE event at IISc bangalore",
			            	   "description":"how was the IEEE event happened at IISc bangalore June 2017?",
			            	   "createdAt":"2017-07-11 16:11:10",
			            	   "createdBy":"Sushant Pawar",
			            	   "upVoteCount":"15",
			            	   "downVoteCount":"5",
			            	   "isVoted":"N",
			            	   "voteValue":"0",
			            	   "tags":"technical",
			            	   "topicUrl":"testTopic3",
			            	   "topicId":"3",
			            	   "commentList" :[
			            	                   {
			            	                	   "id":"4",
			            	                	   "type":"T",
			            	                	   "value":"really good one!",
			            	                	   "createdAt":"2017-07-22 16:11:10",
			            	                	   "createdBy":"Aakash"
			            	                   }
			            	                   ]
			               }
			               ]
	};*/



}

//start generateNewEmptyComment
function generateNewEmptyComment(topicId,topicI,commentListSize,newCommentFieldId,userName,commentListVar){
	var newCommentFieldId="add-comment-"+topicI+"-comment-"+commentListSize;
	var newComment=[];
	newComment.push( "<div class='row well'>");
	newComment.push( "<label class='col-md-offset-0 col-md-1 small' id='newCommentCreatedBy'>" + userName + "</label>");
	var onClickAddCommentFunc="addNewComment(\""+newCommentFieldId+"\",\""+topicId+"\",\""+userName+"\")";
	newComment.push( "<input type='text' class='small col-md-7' id='"+newCommentFieldId+"' name='"+newCommentFieldId+"' placeholder='add new comment'></input>");
	//newComment.push( "&nbsp&nbsp&nbsp&nbsp<input type='button' value='Add' name='addComment' id='addComment' class='btn btn-info btn-xs' onclick='addNewComment('"+newCommentFieldId+"','"+topicId+"','swapnil')'></input>");
	newComment.push( "&nbsp&nbsp&nbsp&nbsp<input type='button' value='Add' name='addComment' id='addComment' class='btn btn-info btn-xs' onclick=\""+onClickAddCommentFunc+"\"></input>");
	//newComment.push( "<input type='button' value='Add Comment' name='newComment' id='newComment' class='btn btn-info btn-sm pull-right' onclick='"+onClickFunc+"'></input>");
	//newComment.push( "<input type='button' value='Add' name='addComment' id='addComment' class='btn btn-info btn-md pull-right' onclick='addNewComment()'></input>");
	newComment.push( "</div>");

	$(newComment.join( "" )).appendTo( "#"+commentListVar);
}//end generateNewEmptyComment

//start addNewComment
function addNewComment(commentFieldId, topicId, userName) {
//	function addNewComment() {
	/* alert("commentFieldId: "+commentFieldId);
	alert("topicId: "+topicId);
	alert("userId: "+userId); */
	var commentValue = document.getElementById(commentFieldId).value;

	if(commentValue==null || commentValue.length==0){
		alert("Comment value should not be empty!");
		return;
	}

	var newCommentURL = "/rest/comment/newComment?dataJson=";
	//alert("commentFieldId: "+commentFieldId +" ;topicId: "+topicId +" ;userName: "+userName +" ;commentValue: "+commentValue);
	var dataJson= "{\"topicId\":\""+topicId+"\",\"userName\":\""+userName+"\",\"commentValue\":\""+commentValue+"\"}";
	$.ajax({
		type: "POST",
		url: newCommentURL+dataJson,
		//dataType: "text",
		success: function(data) {
			//alert(data);
			if(data != null){
				//window.location.href="/rest/topic/getAllTopics";
				//window.location.href="home.html?userName="+username;
				location.reload();
			}else{
				alert("Some error, please try again!");
			}
		}
	});
	/*$.ajax({
		type: "POST",
		url: newCommentURL+dataJson,
		success: function(data) {
			alert(data);
			//if(data.status == "success"){
			if(data != null){
				//location.reload();
			}else{
				alert("Some error, please try again!");
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("Some error. Please try again.");
		}
	});*/
}//end addNewComment

//start syncVotes
function syncVotes(upvoteLblVar,downvoteLblVar,voteValue,upVoteCount,downVoteCount,topicId){
	if (voteValue != null && upvoteLblVar != null && upvoteLblVar != null ) {
		if(voteValue==1) {
			upVoteCount+=1;
			if(downVoteCount>0){
				downVoteCount-=1;
			}

		} else if (voteValue==2) {
			if(upVoteCount>0){
				upVoteCount-=1;
			}
			downVoteCount+=1;
		}

		$("#"+upvoteLblVar).text(""+upVoteCount+" UpVotes");
		//document.getElementById('+downvoteLblVar+').innerHTML = downVoteCount+" DownVotes";
		$("#"+downvoteLblVar).text(""+downVoteCount+" DownVotes");

		//do ajax call and sync with DB
		var userName = $.urlParam("userName");
		var submitVoteURL = "/rest/comment/submitVote?dataJson=";
		var dataJson= "{\"topicId\":\""+topicId+"\",\"userName\":\""+userName+"\",\"voteValue\":\""+voteValue+"\"}";

		$.ajax({
			type: "POST",
			url: submitVoteURL+dataJson,
			success: function(data) {
				//alert(data);
				//if(data.status == "success"){
				if(data != null && data == "Forum_SUCCESS"){
					//location.reload();
				}else{
					alert("Some error, please try again!");
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("Connection error. Please try again.");
			}
		});

	}
}//end syncVotes

//start validateUpDownBtn
function validateUpDownBtn(upvoteBtnVar,downvoteBtnVar,isVoted,voteValue){
	if(isVoted!=null && isVoted=="Y") {
		if(voteValue=="1"){
			//disable upvote button
			if(upvoteBtnVar!=null){
				//alert("1: "+upvoteBtnVar);
				//document.getElementById(upvoteBtnVar).disabled = true;
				$('#'+upvoteBtnVar).attr('disabled','disabled');
			}
		} else if(voteValue=="2"){
			//disable downvote button
			if(downvoteBtnVar!=null){
				//alert("2: "+downvoteBtnVar);
				//document.getElementById(downvoteBtnVar).disabled = true;
				$('#'+downvoteBtnVar).attr('disabled','disabled');
			}
		}
	} else if(isVoted!=null && isVoted=="N" && voteValue=="0") {
		//enable up/down vote buttons
		if(upvoteBtnVar!=null){
			//alert("0: "+upvoteBtnVar);
			//document.getElementById(upvoteBtnVar).disabled = false;
			$('#'+upvoteBtnVar).removeAttr('disabled');
		}
		if(downvoteBtnVar!=null){
			//alert("0: "+downvoteBtnVar);
			//document.getElementById(downvoteBtnVar).disabled = false;
			$('#'+downvoteBtnVar).removeAttr('disabled');
		}
	}
}//end validateUpDownBtn

function formatDateRelative(date) {

	if(date!=null && date.length > 0) {


	}

}

//to formatToLocalDateStr
function formatToLocalDateStr(date) {
	if(date != null && date.length > 0) {
		date = new Date(date);
		return date.toLocaleString();
	} else {
		return null;
	}
}

//to get relative date-time difference from curren-time
function formatDateAbs() {
	//var oldDate="2017-07-24 16:11:10";
	if(oldDate!=null && oldDate.length > 0) {
		var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"];
		oldDate = new Date(oldDate);

		var ddOld = oldDate.getDate();
		var mmOld = oldDate.getMonth(); //January is 0!
		var yyyyOld = oldDate.getFullYear();

		var oldHr;
		var oldMin;
		var oldSec;

		var currentDate = new Date();
		var ddCurrent = currentDate.getDate();
		var mmCurrent = currentDate.getMonth(); //January is 0!
		var yyyyCurrent = currentDate.getFullYear();

		//var dtStr = currentDate.toLocaleString();
		//alert("oldDate: "+oldDate +" ;oldDate.toLocaleString(): "+oldDate.toLocaleString());
		var dateStr = ddCurrent + ", " + monthNames[mmCurrent] + ", " + yyyyCurrent; 

		//alert("dateStr: "+dateStr)






	}
}
$.urlParam = function(name) {
	var results = new RegExp('[\\?&]' + name + '=([^&#]*)')
	.exec(window.location.href);
	if (results != null)
		return results[1] || 0;
	else
		return null;
};
