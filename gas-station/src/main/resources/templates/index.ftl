<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>气瓶信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <style type="text/css">
        /*! normalize.css v8.0.0 | MIT License | github.com/necolas/normalize.css */

        /* Document
           ========================================================================== */

        /**
         * 1. 修正所有浏览器中的行高.
         * 2. 在iOS的切换横竖屏时，防止字体大小的调整.
         */

        html {
            line-height: 1.15; /* 1 */
            -webkit-text-size-adjust: 100%; /* 2 */
        }

        /* Sections
           ========================================================================== */

        /**
         * 删除所有浏览器的margin值。
         */

        body {
            margin: 0;
        }

        /**
         * Correct the font size and margin on `h1` elements within `section` and
         * `article` contexts in Chrome, Firefox, and Safari.
         * 修正h1元素的font-size和margin值
         */

        h1 {
            font-size: 2em;
            margin: 0.67em 0;
        }

        /* Grouping content
           ========================================================================== */

        /**
         * 1. Add the correct box sizing in Firefox.
         * 2. Show the overflow in Edge and IE.
         * 1.修正火狐浏览器的box-sizing
         */

        hr {
            box-sizing: content-box; /* 1 */
            height: 0; /* 1 */
            overflow: visible; /* 2 */
        }

        /**
         * 1. Correct the inheritance and scaling of font size in all browsers.
         * 1. 在所有浏览器中纠正字体大小的继承和扩展。
         * 2. Correct the odd `em` font sizing in all browsers.
         * 2. 在所有浏览器中纠正奇数的字体大小。
         */

        pre {
            font-family: monospace, monospace; /* 1 */
            font-size: 1em; /* 2 */
        }

        /* Text-level semantics：文本级别的语义
           ========================================================================== */

        /**
         * Remove the gray background on active links in IE 10.
         * 删除IE 10中活跃链接的灰色背景。
         */

        a {
            background-color: transparent;
        }

        /**
         * 1. Remove the bottom border in Chrome 57-
         * 1. 移除Chrome 57-浏览器的border-bottom
         * 2. Add the correct text decoration in Chrome, Edge, IE, Opera, and Safari.
         * 2. 在Chrome、Edge、IE、Opera和Safari中添加正确的text-decoration。
         */

        abbr[title] {
            border-bottom: none; /* 1 */
            text-decoration: underline; /* 2 */
            text-decoration: underline dotted; /* 2 */
        }

        /**
         * Add the correct font weight in Chrome, Edge, and Safari.
         * 在Chrome、Edge和Safari中添加正确的font-weight
         */

        b,
        strong {
            font-weight: bolder;
        }

        /**
         * 1. Correct the inheritance and scaling of font size in all browsers.
         * 1.在所有浏览器中纠正字体大小的继承和扩展。
         * 2. Correct the odd `em` font sizing in all browsers.
         */

        code,
        kbd,
        samp {
            font-family: monospace, monospace; /* 1 */
            font-size: 1em; /* 2 */
        }

        /**
         * Add the correct font size in all browsers.
         */

        small {
            font-size: 80%;
        }

        /**
         * Prevent `sub` and `sup` elements from affecting the line height in
         * all browsers.
         * 防止“sub”和“sup”元素影响所有浏览器的线高度。
         */

        sub,
        sup {
            font-size: 75%;
            line-height: 0;
            position: relative;
            vertical-align: baseline;
        }

        sub {
            bottom: -0.25em;
        }

        sup {
            top: -0.5em;
        }

        /* Embedded content：嵌入命令
           ========================================================================== */

        /**
         * Remove the border on images inside links in IE 10.
         */

        img {
            border-style: none;
        }

        /* Forms
           ========================================================================== */

        /**
         * 1. Change the font styles in all browsers.
         * 2. Remove the margin in Firefox and Safari.
         */

        button,
        input,
        optgroup,
        select,
        textarea {
            font-family: inherit; /* 1 */
            font-size: 100%; /* 1 */
            line-height: 1.15; /* 1 */
            margin: 0; /* 2 */
        }

        /**
         * Show the overflow in IE.
         * 1. Show the overflow in Edge.
         */

        button,
        input { /* 1 */
            overflow: visible;
        }

        /**
         * Remove the inheritance of text transform in Edge, Firefox, and IE.
         * 在Edge、Firefox和IE中删除文本转换的继承。
         * 1. Remove the inheritance of text transform in Firefox.
         * 1. 删除Firefox中文本转换的继承。
         */

        button,
        select { /* 1 */
            text-transform: none;
        }

        /**
         * Correct the inability to style clickable types in iOS and Safari.
         * 纠正在iOS和Safari中无法使用可点击类型的功能。
         */

        button,
        [type="button"],
        [type="reset"],
        [type="submit"] {
            -webkit-appearance: button;
        }

        /**
         * Remove the inner border and padding in Firefox.
         * 移除Firefox中border和padding
         */

        button::-moz-focus-inner,
        [type="button"]::-moz-focus-inner,
        [type="reset"]::-moz-focus-inner,
        [type="submit"]::-moz-focus-inner {
            border-style: none;
            padding: 0;
        }

        /**
         * Restore the focus styles unset by the previous rule.
         * 恢复以前规则未设置的焦点样式。
         */

        button:-moz-focusring,
        [type="button"]:-moz-focusring,
        [type="reset"]:-moz-focusring,
        [type="submit"]:-moz-focusring {
            outline: 1px dotted ButtonText;
        }

        /**
         * Correct the padding in Firefox.
         * 修正火狐的padding值
         */

        fieldset {
            padding: 0.35em 0.75em 0.625em;
        }

        /**
         * 1. Correct the text wrapping in Edge and IE.
         * 1.
         * 2. Correct the color inheritance from `fieldset` elements in IE.
         * 2. 从IE的“fieldset”元素中纠正颜色继承。
         * 3. Remove the padding so developers are not caught out when they zero out
         *    `fieldset` elements in all browsers.
         * 3.
         */

        legend {
            box-sizing: border-box; /* 1 */
            color: inherit; /* 2 */
            display: table; /* 1 */
            max-width: 100%; /* 1 */
            padding: 0; /* 3 */
            white-space: normal; /* 1 */
        }

        /**
         * Add the correct vertical alignment in Chrome, Firefox, and Opera.
         * 在Chrome、Firefox和Opera中添加正确的垂直对齐方式。
         */

        progress {
            vertical-align: baseline;
        }

        /**
         * Remove the default vertical scrollbar in IE 10+.
         * 删除IE 10+中的默认垂直滚动条
         */

        textarea {
            overflow: auto;
        }

        /**
         * 1. Add the correct box sizing in IE 10.
         * 1. 修正IE10中的box-sizing
         * 2. Remove the padding in IE 10.
         * 2. 移除IE10的padding值
         */

        [type="checkbox"],
        [type="radio"] {
            box-sizing: border-box; /* 1 */
            padding: 0; /* 2 */
        }

        /**
         * Correct the cursor style of increment and decrement buttons in Chrome.
         * 修正Chrome中增加和减量按钮的光标样式。
         */

        [type="number"]::-webkit-inner-spin-button,
        [type="number"]::-webkit-outer-spin-button {
            height: auto;
        }

        /**
         * 1. Correct the odd appearance in Chrome and Safari.
         * 1. 修正在Chrome和Safari中出现的奇怪现象。
         * 2. Correct the outline style in Safari.
         * 2. 修正Safari中的outline样式
         */

        [type="search"] {
            -webkit-appearance: textfield; /* 1 */
            outline-offset: -2px; /* 2 */
        }

        /**
         * Remove the inner padding in Chrome and Safari on macOS.
         * 移除chrome和macos上的Safari的padding值
         */

        [type="search"]::-webkit-search-decoration {
            -webkit-appearance: none;
        }

        /**
         * 1. Correct the inability to style clickable types in iOS and Safari.
         * 1. 纠正在iOS和Safari中无法使用可点击类型的功能。
         * 2. Change font properties to `inherit` in Safari.
         * 2. 在Safari中更改字体属性，以“inherit”。
         */

        ::-webkit-file-upload-button {
            -webkit-appearance: button; /* 1 */
            font: inherit; /* 2 */
        }

        /* Interactive：交互式的
           ========================================================================== */

        /*
         * Add the correct display in Edge, IE 10+, and Firefox.
         * 修正Edge, IE 10+,和 Firefox中details标签的display属性
         */

        details {
            display: block;
        }

        /*
         * Add the correct display in all browsers.
         * 修正所有浏览器的display属性
         */

        summary {
            display: list-item;
        }

        /* Misc
           ========================================================================== */

        /**
         * Add the correct display in IE 10+.
         * 修正IE10+的template元素的display
         */

        template {
            display: none;
        }

        /**
         * Add the correct display in IE 10.
         * 修正IE 10中的display
         */
        [hidden] {
            display: none;
        }

        .main {

        }
        .main .title {
            font-size: 18px;
            text-align: center;
            color: #ffffff;
            background-color: #3388ff;
            line-height: 42px;
        }
        .main .body {
            background-color: #efefef;
        }
        .tabs {
            display: flex;
            margin-bottom: 12px;
            background-color: #ffffff;
        }
        .tabs .tab {
            flex: 1;
            text-align: center;
            font-weight: bold;
            color: #333;
            font-size: 16px;
            line-height: 2.8;
            margin: 0 5px;
        }
        .tabs .tab.active {
            border-bottom: 2px solid #3388ff;
        }
        .body .content {
            background-color: #ffffff;
        }
        .list {
            margin: 0;
            padding: 0 16px;
            border-top: 1px solid #eee;
        }
        .list .list-item {
            display: flex;
            font-size: 14px;
            line-height: 3;
            border-top: 1px solid #eee;
            border-bottom: 1px solid #eee;
            margin: -1px 0;
        }
        .list .list-item .list-item-title {
            width: 80px;
            text-align: right;
            padding-right: 5px;
        }
        .list .list-item .list-item-desc {
            flex: 1;
            text-align: left;
        }
        .none {
            display: none;
        }
    </style>
</head>
<body>
<div class="main">
    <div class="title">气瓶信息</div>
    <div class="body">
        <div id="tabs" class="tabs">
            <div class="tab active" data-target="gasBottle">气瓶信息</div>
            <div class="tab" data-target="fillLog">充装信息</div>
            <div class="tab" data-target="anquan">安全须知</div>
            <div class="tab" data-target="qiye">企业信息</div>
        </div>
        <div id="content" class="content">
            <ul id="gasBottle" class="list">
                <li class="list-item">
                    <span class="list-item-title">气瓶编号:</span>
                    <span class="list-item-desc">1234567890</span>
                </li>
                <li class="list-item">
                    <span class="list-item-title">气瓶编号:</span>
                    <span class="list-item-desc">1234567890</span>
                </li>
                <li class="list-item">
                    <span class="list-item-title">气瓶编号:</span>
                    <span class="list-item-desc">1234567890</span>
                </li>
            </ul>
            <ul id="fillLog" class="list none">
                <li class="list-item">
                    <span class="list-item-title">充装时间:</span>
                    <span class="list-item-desc">1234567890</span>
                </li>
                <li class="list-item">
                    <span class="list-item-title">充装时间:</span>
                    <span class="list-item-desc">1234567890</span>
                </li>
                <li class="list-item">
                    <span class="list-item-title">充装时间:</span>
                    <span class="list-item-desc">1234567890</span>
                </li>
            </ul>
            <div id="anquan" class="none" style="padding: 16px;">${anquan}</div>
            <div id="qiye" class="none" style="padding: 16px;">${qiye}</div>
        </div>
    </div>
</div>
<script>
    const content = document.querySelector('#content');
    const tabs = document.querySelector('#tabs');
    tabs.addEventListener('click', function(event) {
        for (let child of tabs.children) {
            child.classList.remove('active');
        }
        event.target.classList.add('active');
        for (let child of content.children) {
            child.classList.add('none');
        }
        const target = event.target.dataset.target;
        document.querySelector("#" + target).classList.remove("none");
    });
</script>
</body>
</html>