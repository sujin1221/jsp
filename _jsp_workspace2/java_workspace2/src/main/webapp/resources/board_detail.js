async function getCommentListFromServer(bno){
    try{
        const resp = await fetch("/cmt/list/" + bno);
        const result = await resp.json();
        return result;
    }catch(error){
        console.log(error);
    }
}

function spreadCommentList(result){
    console.log("comment list >> " + result);
    let div = document.getElementById("commentLine");
    let writer = document.getElementById("cmtWriter").value;
    
    div.innerHTML ="";
    for(let i=0; i<result.length; i++){
        let html = '<div>';
        html += `<div>${result[i].cno},${result[i].bno},${result[i].writer},${result[i].regdate}</div>`;
        html += `<div>`;
        html += `<input type="text" class="cmtText" value="${result[i].content}">`;
        if (result[i].writer == writer) { 
        html += `<button type=button data-cno="${result[i].cno}" class="cmtModBtn">수정</button>`;
        html += `<button type=button data-cno="${result[i].cno}" class="cmtDelBtn">삭제</button><br>`;
        }
        html += `</div>`;
        html += `</div><br><hr>`;
        div.innerHTML += html;
    }
}

function printCommentList(bno){
    getCommentListFromServer(bno).then(result => {
        console.log(result);
        if(result.length > 0){
            spreadCommentList(result);
        }else{
            let div = document.getElementById("commentLine");
            innerHTML = `<div>comment가 없습니다.</div>`;
        }
    });
}

document.getElementById('cmtAddBtn').addEventListener('click',()=>{
    const cmtText = document.getElementById('cmtText').value;
    if(cmtText == null || cmtText == ''){
        alert('댓글을 입력해주세요.');        
        return false;
    }else{ //아니라면 댓글이 등록되도록 설정
        let cmtData = {
            bno: bnoVal,
            writer: document.getElementById('cmtWriter').value,
            content: cmtText
        };
        document.getElementById('cmtText').value = "";
        //댓글 등록 비동기 통신 호출
        postCommentToserver(cmtData).then(result=>{
            console.log(result);
            if(result>0){
                alert('comment post success!'); //성공문구 출력
            }
            printCommentList(bnoVal); //출력
        });
        
    }
})

async function postCommentToserver(cmtData){
    try{
        const url = "/cmt/post";
        const config ={
            method: 'post',
            headers:{
                'Content-Type' : 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };

        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    }catch(error){
        console.log(error);
    }
}

//수정 : cno, content
async function updateCommentFromserver(cnoVal, cmtText){
    try {
        const url = "/cmt/modify";
        const config ={
            method: 'post',
            headers:{
                'Content-Type' : 'application/json; charset-utf-8'
            },
            body: JSON.stringify({cno:cnoVal, content:cmtText})
        }

        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function removeCommentFromserver(cnoVal){
    try {
        const url = "/cmt/remove?cnoVal="+cnoVal;
        const resp = await fetch(url);
        const result = await resp.text(); 
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.addEventListener('click',(e)=>{
    console.log(e.target);
    //삭제버튼이 클릭되면 ... 수정버튼이 클릭되면
    if(e.target.classList.contains('cmtDelBtn')){
        let cnoVal = e.target.dataset.cno;
        console.log(cnoVal);
        removeCommentFromserver(cnoVal).then(result =>{
            //result = isOk;
            if(result > 0 ){
                alert('comment delete success!');
                printCommentList(bnoVal);
            }
        })
    }

    if(e.target.classList.contains('cmtModBtn')){ 
        let cnoVal = e.target.dataset.cno;
        console.log(cnoVal);
        let div = e.target.closest('div');
        let cmtText = div.querySelector('.cmtText').value;
        console.log(cmtText);
        console.log(div);

        updateCommentFromserver(cnoVal,cmtText).then(result=>{
              if(result > 0 ){
                alert('댓글수정 성공~!!');
                printCommentList(bnoVal);
            }
        })
    }
})