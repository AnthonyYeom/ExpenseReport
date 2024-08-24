
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>

<head>
    <title>ì½ë© ì ë¬¸ê°ë¥¼ ë§ë¤ê¸° ìí ì¨ë¼ì¸ ê°ì ìì¤í</title>
    <meta charset="UTF-8">
    <title>ê³µì§ì¬í­ëª©ë¡</title>

    <link href="/css/customer/layout.css" type="text/css" rel="stylesheet" />
    <style>
        #visual .content-container {
            height: inherit;
            display: flex;
            align-items: center;

            background: url("/images/mypage/visual.png") no-repeat center;
        }
    </style>
</head>

<body>
    <!-- header ë¶ë¶ -->

    <header id="header">

        <div class="content-container">
            <!-- ---------------------------<header>--------------------------------------- -->

            <h1 id="logo">
                <a href="/index.html">
                    <img src="/images/logo.png" alt="ë´ë ì² ì¨ë¼ì¸" />

                </a>
            </h1>

            <section>
                <h1 class="hidden">í¤ë</h1>

                <nav id="main-menu">
                    <h1>ë©ì¸ë©ë´</h1>
                    <ul>
                        <li><a href="/guide">íìµê°ì´ë</a></li>

                        <li><a href="/course">ê°ì¢ì í</a></li>
                        <li><a href="/answeris/index">AnswerIs</a></li>
                    </ul>
                </nav>

                <div class="sub-menu">

                    <section id="search-form">
                        <h1>ê°ì¢ê²ì í¼</h1>
                        <form action="/course">
                            <fieldset>
                                <legend>ê³¼ì ê²ìíë</legend>
                                <label>ê³¼ì ê²ì</label>
                                <input type="text" name="q" value="" />
                                <input type="submit" value="ê²ì" />
                            </fieldset>
                        </form>
                    </section>

                    <nav id="acount-menu">
                        <h1 class="hidden">íìë©ë´</h1>
                        <ul>
                            <li><a href="/index.html">HOME</a></li>



                            <li>
                                <form action="/logout" method="post">
                                    <input type="hidden" name="" value="" />
                                    <input type="submit" value="ë¡ê·¸ìì"
                                        style="border:none;background: none;vertical-align: middle;font-size: 10px;color:#979797;font-weight: bold;" />

                                </form>
                            </li>

                            <li><a href="/member/agree">íìê°ì</a></li>
                        </ul>
                    </nav>

                    <nav id="member-menu" class="linear-layout">
                        <h1 class="hidden">ê³ ê°ë©ë´</h1>
                        <ul class="linear-layout">
                            <li><a href="/member/home"><img src="/images/txt-mypage.png" alt="ë§ì´íì´ì§" /></a></li>
                            <li><a href="/notice/list.html"><img src="/images/txt-customer.png" alt="ê³ ê°ì¼í°" /></a></li>
                        </ul>
                    </nav>

                </div>
            </section>

        </div>

    </header>

    <!-- --------------------------- <visual> --------------------------------------- -->
    <!-- visual ë¶ë¶ -->

    <div id="visual">
        <div class="content-container"></div>
    </div>
    <!-- --------------------------- <body> --------------------------------------- -->
    <div id="body">
        <div class="content-container clearfix">

            <!-- --------------------------- aside --------------------------------------- -->
            <!-- aside ë¶ë¶ -->


            <aside class="aside">
                <h1>ADMIN PAGE</h1>

                <nav class="menu text-menu first margin-top">
                    <h1>ë§ì´íì´ì§</h1>
                    <ul>
                        <li><a href="/admin/index.html">ê´ë¦¬ìí</a></li>
                        <li><a href="/teacher/index.html">ì ìëíì´ì§</a></li>
                        <li><a href="/student/index.html">ìê°ìíì´ì§</a></li>
                    </ul>
                </nav>

                <nav class="menu text-menu">
                    <h1>ìë¦¼ê´ë¦¬</h1>
                    <ul>
                        <li><a href="/admin/board/notice/list.html">ê³µì§ì¬í­</a></li>
                    </ul>
                </nav>

            </aside>
            <!-- --------------------------- main --------------------------------------- -->




            <main>
                <h2 class="main title">ê³µì§ì¬í­</h2>

                <div class="breadcrumb">
                    <h3 class="hidden">breadlet</h3>
                    <ul>
                        <li>home</li>
                        <li>ê³ ê°ì¼í°</li>
                        <li>ê³µì§ì¬í­</li>
                    </ul>
                </div>

                <div class="margin-top first">
                    <h3 class="hidden">ê³µì§ì¬í­ ë´ì©</h3>
                    <table class="table">
                        <tbody>
                            <tr>
                                <th>ì ëª©</th>
                                <td class="text-align-left text-indent text-strong text-orange" colspan="3">ì¤íë§ 8ê°ê¹ì§ì ìì 
                                    ì½ë</td>
                            </tr>
                            <tr>
                                <th>ìì±ì¼</th>
                                <td class="text-align-left text-indent" colspan="3">2019-08-18 </td>
                            </tr>
                            <tr>
                                <th>ìì±ì</th>
                                <td>newlec</td>
                                <th>ì¡°íì</th>
                                <td>148</td>
                            </tr>
                            <tr>
                                <th>ì²¨ë¶íì¼</th>
                                <td colspan="3"></td>
                            </tr>
                            <tr class="content">
                                <td colspan="4">ìëíì¸ì. ë´ë ì²ìëë¤.<div><br></div>
                                    <div>íì¬ ì§íì¤ì¸ ì¤íë§ DI 8ê°ê¹ì§ì ìì ìëë¤.</div>
                                    <div><br></div>
                                    <div><a href="http://www.newlecture.com/resource/spring2.zip"><b><u>
                                                    <font size="5" color="#dd8a00">ìì  ë¤ì´ë¡ëíê¸°</font>
                                                </u></b></a></div>
                                    <div><br></div>
                                    <div><br></div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="margin-top text-align-center">
                    <a class="btn-text btn-cancel" href="list.html">ëª©ë¡</a>
                    <a class="btn-text btn-default" href="edit.html">ìì </a>
                    <a class="btn-text btn-default" href="del.html">ì­ì </a>
                </div>

                <div class="margin-top">
                    <table class="table border-top-default">
                        <tbody>
                            <tr>
                                <th>ë¤ìê¸</th>
                                <td colspan="3" class="text-align-left text-indent">ë¤ìê¸ì´ ììµëë¤.</td>
                            </tr>
                            <tr>
                                <th>ì´ì ê¸</th>
                                <td colspan="3" class="text-align-left text-indent"><a class="text-blue text-strong"
                                        href="">ì¤íë§ DI ìì  ì½ë</a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </main>

        </div>
    </div>

    <!-- ------------------- <footer> --------------------------------------- -->



    <footer id="footer">
        <div class="content-container">
            <h2 id="footer-logo"><img src="/images/logo-footer.png" alt="íì¬ì ë³´"></h2>

            <div id="company-info">
                <dl>
                    <dt>ì£¼ì:</dt>
                    <dd>ìì¸í¹ë³ì </dd>
                    <dt>ê´ë¦¬ìë©ì¼:</dt>
                    <dd>admin@newlecture.com</dd>
                </dl>
                <dl>
                    <dt>ì¬ìì ë±ë¡ë²í¸:</dt>
                    <dd>111-11-11111</dd>
                    <dt>íµì  íë§¤ì:</dt>
                    <dd>ì ê³ ì  1111 í¸</dd>
                </dl>
                <dl>
                    <dt>ìí¸:</dt>
                    <dd>ë´ë ì²</dd>
                    <dt>ëí:</dt>
                    <dd>íê¸¸ë</dd>
                    <dt>ì íë²í¸:</dt>
                    <dd>111-1111-1111</dd>
                </dl>
                <div id="copyright" class="margin-top">Copyright â newlecture.com 2012-2014 All Right Reserved.
                    Contact admin@newlecture.com for more information</div>
            </div>
        </div>
    </footer>
</body>

</html>