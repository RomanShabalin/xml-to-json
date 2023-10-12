window.prettyPrint = function prettyPrint() {
    let ugly = document.getElementById('textArea').value;
    let obj = JSON.parse(ugly);
    document.getElementById('textArea').value = JSON.stringify(obj, undefined, 4);
}
