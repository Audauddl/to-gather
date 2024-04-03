
 (function(){
        const previewArea = document.querySelector("#preview-area");
        const fileElements = document.querySelector("#attach-profile");

        fileElements.addEventListener('change', preview);

        function preview() {
            console.log(this);
            if(this.files && this.files[0]) {
                const reader = new FileReader();
                reader.readAsDataURL(this.files[0]);
                reader.onload = function() {
                    previewArea.innerHTML = `<img src='${reader.result}' id="preview-image">`;
                }
            }
        }
    })();
    
 
