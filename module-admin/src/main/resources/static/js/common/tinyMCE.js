var tinimceInit = function (options) {

    var option = {
        target: 'editor'
    };

    $.extend(options, option);

    var tinyEditor = tinymce.init({
        selector: "."+option.target,
        min_height: 500,
        max_height: 1000,
        menubar: false,
        paste_as_text: true,
        fullpage_default_font_size: "14px",
        branding: false,
        plugins: "autolink code link autoresize paste contextmenu image preview imagetools",
        toolbar: "undo redo | fontsizeselect | forecolor | bold italic strikethrough underline | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link custom_image | code preview",
        fontsize_formats: '10px 12px 14px 16px 18px 20px 22px 24px 28px 32px 36px 48px',
        relative_urls: false,
        setup: function(editor) {
            editor.ui.registry.addButton('custom_image', {
                icon: 'image',
                tooltip: 'insert Image',
                onAction: function () {
                    documentUpload({
                        multiple: false,
                        accept: '.jpg, .png',
                        callback: function (data) {
                            if (data.rs_st === 0) {
                                var fileInfo = data.rs_data;
                                tinymce.execCommand('mceInsertContent', false,
                                    /**
                                    "<img src='" + fileInfo.thumbnailPath + "' data-mce-src='" + fileInfo.thumbnailPath + "' data-originalFileName='" + fileInfo.orgFilename + "' >");
                                     **/
                                    "<img src='" + fileInfo.thumbnailPath + "' data-originalFileName='" + fileInfo.orgFilename + "' >");
                            }
                        }
                    });
                }
            });
        }
    });
};
