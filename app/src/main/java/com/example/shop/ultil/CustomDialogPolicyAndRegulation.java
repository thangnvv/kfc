package com.example.shop.ultil;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.shop.R;

public class CustomDialogPolicyAndRegulation extends Dialog {

    TextView mTxtViewPolicyAndRegulation;
    ImageButton mImgButtonPolicyAndRegulation;
    private String policy;
    public CustomDialogPolicyAndRegulation(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_policy_and_regulation);
        mTxtViewPolicyAndRegulation = findViewById(R.id.textViewPolicyAndRegulation);
        mTxtViewPolicyAndRegulation.setMovementMethod(LinkMovementMethod.getInstance());

        mImgButtonPolicyAndRegulation = findViewById(R.id.imageButtonClosePolicyAndRegulation);
        mImgButtonPolicyAndRegulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        addPolicy();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            mTxtViewPolicyAndRegulation.setText(Html.fromHtml(policy,Html.FROM_HTML_MODE_COMPACT));
        } else {
            mTxtViewPolicyAndRegulation.setText(Html.fromHtml(policy));
        }

    }

    private void addPolicy() {
        policy = "<html>\n" +
                "<body>\n" +
                "<h2 style=\"text-align: center;\"><strong>Chính sách bảo mật thông tin</strong></h2>\n" +
                "<p><strong><br>A. ĐỐI VỚI WEBSITE</strong></p>\n" +
                "<p><strong><br></strong>1/ Mục đích và phạm vi thu thập</p>\n" +
                "<p><br>KFC Việt Nam công nhận và tôn trọng thông tin bảo mật của các cá nhân đăng nhập vào website của KFC Việt Nam. Chính sách này bao gồm những thông tin cá nhân mà KFC Việt Nam và các đơn vị nhượng quyền đang nắm giữ và trình bày theo cách mà KFC Việt Nam và các đơn vị nhượng quyền muốn sử dụng, quản lý và bảo vệ thông tin cá nhân của khách hàng khi khách hàng đăng nhập vào website của KFC Việt Nam hoặc đặt hàng qua website của KFC Việt Nam.</p>\n" +
                "<p><br>KFC Việt Nam có thể điều chỉnh chính sách bảo mật này bất cứ khi nào chúng tôi thấy cần thiết, vì vậy vui lòng truy cập vào website thường xuyên để cập nhật thông tin mới. Chính sách bảo mật thông tin này có hiệu lực kể từ tháng 01 năm 2015.</p>\n" +
                "<p><br>KFC Việt Nam có thể thu thập thông tin cá nhân từ khách hàng bao gồm họ tên, địa chỉ, số điện thoại bàn và số điện thoại di động, địa chỉ email, nội dung thẻ tín dụng, và bất kỳ thông tin nào khác nếu bạn đồng ý cung cấp cho chúng tôi những thông tin này. Khi khách hàng sử dụng hệ thống đặt hàng qua mạng, khách hàng cần phải trả lời thêm những thông tin yêu cầu. Những thông tin này giúp cho việc phân phối sản phẩm đến nhà khách hàng nhanh chóng dễ dàng hơn. Hệ thống đặt hàng qua mạng cũng sẽ lưu trữ thông tin về đơn đặt hàng của khách giúp cho khách hàng ghi nhớ và đặt lại thực đơn cho lần mua hàng sau.</p>\n" +
                "<p><br>Nếu KFC Việt Nam thu thập thông tin cá nhân của khách hàng từ người khác, KFC Việt Nam sẽ thực hiện các trình tự hợp lý để thông báo đến khách hàng.</p>\n" +
                "<p><br>Khách hàng sẽ tự chịu trách nhiệm về bảo mật và lưu giữ mọi hoạt động sử dụng dịch vụ dưới tên đăng ký, mật khẩu và hộp thư điện tử của mình. Ngoài ra, khách hàng có trách nhiệm thông báo kịp thời cho webiste <strong><u><a href=\"http://kfcvietnam.com.vn/\">kfcvietnam.com.vn</a></u></strong> về những hành vi sử dụng trái phép, lạm dụng, vi phạm bảo mật, lưu giữ tên đăng ký và mật khẩu của bên thứ ba để có biện pháp giải quyết phù hợp.</p>\n" +
                "<p><strong><br>2/ Phạm vi sử dụng thông tin</strong></p>\n" +
                "<p><br>KFC có thể sử dụng thông tin cá nhân do khách hàng cung cấp và xử lý những thông tin này để cung cấp hàng hóa và dịch vụ cho khách hàng. Thông thường, chúng tôi sẽ sử dụng những thông tin cá nhân này theo cách mà khách hàng mong muốn, bao gồm nhưng không giới hạn cho bất cứ mục đích nào như sau:</p>\n" +
                "<p><br>- Giới thiệu và cung cấp các phần thức ăn nhanh, các chương trình khuyến mãi mới nhất đến khách hàng hoặc những thông tin về sản phẩm và dịch vụ mà KFC Việt Nam xét thấy có lợi cho khách hàng.</p>\n" +
                "<p><br>- Xác nhận đơn hàng hoặc xác nhận tình trạng đăng ký khách hàng.</p>\n" +
                "<p><br>- Gửi các thông báo về các hoạt động trao đổi thông tin giữa khách hàng và website <strong><u><a href=\"http://kfcvietnam.com.vn/\">kfcvietnam.com.vn</a></u></strong>.</p>\n" +
                "<p><br>- Ngăn ngừa các hoạt động phá hủy tài khoản người dùng của khách hàng hoặc các hoạt động giả mạo khách hàng.</p>\n" +
                "<p><br>- Liên lạc, cung cấp cho khách hàng bất cứ thông tin gì về công ty mà khách hàng yêu cầu và giải quyết với khách hàng trong những trường hợp đặc biệt.</p>\n" +
                "<p><br>- Xác định số lượng khách hàng truy cập vào website của KFC Việt Nam.</p>\n" +
                "<p><br>- Thông báo cho khách hàng biết những thay đổi trên website của KFC Việt Nam.</p>\n" +
                "<p><br>- Quản lý chương trình nghiên cứu tiếp thị.</p>\n" +
                "<p><br>- Không sử dụng thông tin cá nhân của khách hàng ngoài mục đích xác nhận và liên hệ có liên quan đến giao dịch tại <strong><u><a href=\"http://kfcvietnam.com.vn/\">kfcvietnam.com.vn</a></u></strong>.</p>\n" +
                "<p><strong><br>B. ĐỐI VỚI ỨNG DỤNG “KFC VIETNAM”</strong></p>\n" +
                "<p><strong><br>1/ Mục đích và phạm vi thu thập</strong></p>\n" +
                "<p><br>KFC Việt Nam công nhận và tôn trọng thông tin bảo mật của các cá nhân đăng nhập vào ứng dụng của KFC Việt Nam. Chính sách này bao gồm những thông tin cá nhân mà KFC Việt Nam và các đơn vị nhượng quyền đang nắm giữ và trình bày theo cách mà KFC Việt Nam và các đơn vị nhượng quyền muốn sử dụng, quản lý và bảo vệ thông tin cá nhân của khách hàng khi khách hàng đăng nhập vào ứng dụng của KFC Việt Nam hoặc đặt hàng qua ứng dụng của KFC Việt Nam.</p>\n" +
                "<p><br>KFC Việt Nam có thể điều chỉnh chính sách bảo mật này bất cứ khi nào chúng tôi thấy cần thiết, vì vậy vui lòng truy cập vào ứng dụng thường xuyên để cập nhật thông tin mới. Chính sách bảo mật thông tin này có hiệu lực kể từ tháng 09 năm 2018.</p>\n" +
                "<p><br>* Những thông tin được thu thập trên thiết bị di động và/hoặc những tính năng của thiết bị di động được liên kết hoạt động khi khởi chạy ứng dụng:</p>\n" +
                "<p><br>- Nhận dạng:</p>\n" +
                "<p><br>Sử dụng một hoặc nhiều: tài khoản trên thiết bị, dữ liệu tiểu sử</p>\n" +
                "<p><br>- Danh bạ:</p>\n" +
                "<p><br>Sử dụng thông tin liên hệ</p>\n" +
                "<p><br>- Vị trí:</p>\n" +
                "<p><br>Sử dụng vị trí của thiết bị</p>\n" +
                "<p><br>- Điện thoại:</p>\n" +
                "<p><br>Cho phép ứng dụng tạo và quản lý cuộc gọi điện thoại. Bạn có thể phải trả phí.</p>\n" +
                "<p><br>- Ảnh/phương tiện/tệp:</p>\n" +
                "<p><br>Sử dụng một hoặc nhiều trong số: các tệp trên thiết bị như hình ảnh, video hoặc âm thanh; bộ nhớ ngoài của thiết bị</p>\n" +
                "<p><br>- Máy ảnh:</p>\n" +
                "<p><br>Sử dụng máy ảnh của thiết bị</p>\n" +
                "<p><br>Ngoài ra, ứng dụng KFC Việt Nam có thể thu thập thông tin cá nhân từ khách hàng bao gồm họ tên, địa chỉ, số điện thoại bàn và số điện thoại di động, địa chỉ email, nội dung thẻ tín dụng, và bất kỳ thông tin nào khác nếu bạn đồng ý cung cấp cho chúng tôi những thông tin này. Khi khách hàng sử dụng hệ thống đặt hàng qua mạng, khách hàng cần phải trả lời thêm những thông tin yêu cầu. Những thông tin này giúp cho việc phân phối sản phẩm đến nhà khách hàng nhanh chóng dễ dàng hơn. Hệ thống đặt hàng qua mạng cũng sẽ lưu trữ thông tin về đơn đặt hàng của khách giúp cho khách hàng ghi nhớ và đặt lại thực đơn cho lần mua hàng sau.</p>\n" +
                "<p><br>Nếu KFC Việt Nam thu thập thông tin cá nhân của khách hàng từ người khác, KFC Việt Nam sẽ thực hiện các trình tự hợp lý để thông báo đến khách hàng.</p>\n" +
                "<p><br>Khách hàng sẽ tự chịu trách nhiệm về bảo mật và lưu giữ mọi hoạt động sử dụng dịch vụ dưới tên đăng ký, mật khẩu và hộp thư điện tử của mình. Ngoài ra, khách hàng có trách nhiệm thông báo kịp thời cho ứng dụng <strong><a href=\"http://kfcvietnam.com.vn/\">KFC Vietnam</a></strong> về những hành vi sử dụng trái phép, lạm dụng, vi phạm bảo mật, lưu giữ tên đăng ký và mật khẩu của bên thứ ba để có biện pháp giải quyết phù hợp.</p>\n" +
                "<p><strong><br>2/ Phạm vi sử dụng thông tin</strong></p>\n" +
                "<p><br>KFC có thể sử dụng thông tin cá nhân do khách hàng cung cấp và xử lý những thông tin này để cung cấp hàng hóa và dịch vụ cho khách hàng. Thông thường, chúng tôi sẽ sử dụng những thông tin cá nhân này theo cách mà khách hàng mong muốn, bao gồm nhưng không giới hạn cho bất cứ mục đích nào như sau:</p>\n" +
                "<p><br>- Giới thiệu và cung cấp các phần thức ăn nhanh, các chương trình khuyến mãi mới nhất đến khách hàng hoặc những thông tin về sản phẩm và dịch vụ mà KFC Việt Nam xét thấy có lợi cho khách hàng.</p>\n" +
                "<p><br>- Xác nhận đơn hàng hoặc xác nhận tình trạng đăng ký khách hàng.</p>\n" +
                "<p><br>- Gửi các thông báo về các hoạt động trao đổi thông tin giữa khách hàng và ứng dụng <strong><a href=\"http://kfcvietnam.com.vn/\">KFC Vietnam</a></strong>.</p>\n" +
                "<p><br>- Ngăn ngừa các hoạt động phá hủy tài khoản người dùng của khách hàng hoặc các hoạt động giả mạo khách hàng.</p>\n" +
                "<p><br>- Liên lạc, cung cấp cho khách hàng bất cứ thông tin gì về công ty mà khách hàng yêu cầu và giải quyết với khách hàng trong những trường hợp đặc biệt.</p>\n" +
                "<p><br>- Xác định số lượng khách hàng truy cập vào ứng dụng của KFC Việt Nam.</p>\n" +
                "<p><br>- Thông báo cho khách hàng biết những thay đổi trên ứng dụng của KFC Việt Nam.</p>\n" +
                "<p><br>- Quản lý chương trình nghiên cứu tiếp thị.</p>\n" +
                "<p><br>- Không sử dụng thông tin cá nhân của khách hàng ngoài mục đích xác nhận và liên hệ có liên quan đến giao dịch tại ứng dụng <strong><a href=\"http://kfcvietnam.com.vn/\">KFC Vietnam</a></strong>.</p>\n" +
                "<p><strong><br>C. CÁC CHÍNH SÁCH CHUNG KHÁC</strong></p>\n" +
                "<p><strong><br>1/ Chia sẻ thông tin</strong></p>\n" +
                "<p><br>KFC Việt Nam sẽ không cung cấp bất kỳ thông tin cá nhân nào của khách hàng cho bên thứ ba không liên quan để bên thứ ba có thể sử dụng những thông tin này tiếp thị trực tiếp đến khách hàng. KFC Việt Nam có thể sử dụng các công ty có liên quan để vận hành và bảo trì ứng dụng hoặc cho những mục đích khác có liên quan đến hoạt động kinh doanh, và những công ty này sẽ nhận thông tin của khách hàng để thực hiện những yêu cầu trên. KFC Việt Nam có quyền chia sẻ thông tin cá nhân của khách hàng trong một số trường hợp cơ quan chính phủ có yêu cầu về thông tin, phục vụ mục đích điều tra hoặc những yêu cầu khác theo quy định của pháp luật.</p>\n" +
                "<p><br>Các thông tin cá nhân mà khách hàng đã đăng ký trên website hoặc ứng dụng của KFC Việt Nam có thể được chia sẻ cho bên thứ ba:</p>\n" +
                "<p><br>- Những nhà cung cấp do chúng tôi thuê để cung cấp một số dịch vụ như gửi thư đến cho khách hàng.</p>\n" +
                "<p><br>- Để đáp ứng mục đích của khách hàng khi đăng ký thông tin cá nhân.</p>\n" +
                "<p><br>- Nếu khách hàng đồng ý chia sẻ những thông tin cá nhân này.</p>\n" +
                "<p><br>- Nếu chính quyền yêu cầu chia sẻ những thông tin cá nhân này.</p>\n" +
                "<p><br>- Nếu thông tin cá nhân của khách hàng do đơn vị tiếp thị thu thập thì sẽ được cung cấp cho đơn vị tiếp thị này với mục đích nghiên cứu & tiếp thị.</p>\t\n" +
                "<p><strong><br>2/ Thời gian lưu trữ thông tin</strong></p>\n" +
                "<p><br>Dữ liệu cá nhân của khách hàng sẽ được lưu trữ cho đến khi có yêu cầu hủy bỏ hoặc tự khách hàng đăng nhập và thực hiện hủy bỏ. Còn lại trong mọi trường hợp thông tin cá nhân khách hàng sẽ được bảo mật trên máy chủ của KFC Việt Nam.</p>\n" +
                "<p><br>Khách hàng có quyền tự kiểm tra, cập nhật, điều chỉnh hoặc hủy bỏ thông tin cá nhân của mình bằng cách đăng nhập vào tài khoản và chỉnh sửa thông tin cá nhân hoặc yêu cầu KFC Việt Nam thực hiện việc này.</p>\n" +
                "<p><strong><br>3/ Địa chỉ của đơn vị thu thập và quản lý thông tin cá nhân</strong></p>\n" +
                "\n" +
                "<p><br>- <strong>Công ty Liên Doanh TNHH KFC Việt Nam</strong></p>\n" +
                "<p><br>- Mã số thuế/ Số QĐ thành lập: <strong>0100773885</strong></p>\n" +
                "<p><br>- Địa chỉ: <strong>292 Bà Triệu– P. Lê Đại Hành – Q. Hai Bà Trưng – Hà Nội</strong></p>\n" +
                "<p><br>- Điện thoại: <strong>028.38489828</strong></p>\n" +
                "<p><br>- <strong>Email:</strong> <a href=\"mailto:lienhe@kfcvietnam.com.vn\">lienhe@kfcvietnam.com.vn</a></p>\n" +
                "<p><br>Khách hàng có quyền gửi khiếu nại về đến ban quản trị <strong>website hoặc ứng dụng của </strong><strong>KFC Việt Nam</strong>. Khi tiếp nhận những phản hồi này, KFC Việt Nam sẽ xác nhận lại thông tin, trường hợp đúng như phản ánh của khách hàng tùy theo mức độ, KFC Việt Nam sẽ có những biện pháp xử lý kịp thời.</p>\n" +
                "<p><br><strong><em>4/</em></strong><strong> <em>Cam kết bảo mật thông tin cá nhân khách hàng</em></strong></p>\n" +
                "<p>Thông tin cá nhân của khách hàng trên KFC Việt Nam được KFC Việt Nam cam kết bảo mật tuyệt đối theo chính sách bảo vệ thông tin cá nhân của <strong>KFC Việt Nam</strong>. Việc thu thập và sử dụng thông tin của mỗi khách hàng chỉ được thực hiện khi có sự đồng ý của khách hàng đó trừ những trường hợp pháp luật có quy định khác.</p>\n" +
                "<p><br>Không sử dụng, không chuyển giao, cung cấp hay tiết lộ cho bên thứ 3 nào về thông tin cá nhân của khách hàng khi không có sự cho phép đồng ý từ khách hàng.</p>\n" +
                "<p><br>Trong trường hợp máy chủ lưu trữ thông tin bị hacker tấn công dẫn đến mất mát dữ liệu cá nhân khách hàng, <strong>KFC Việt Nam</strong> sẽ có trách nhiệm thông báo vụ việc cho cơ quan chức năng điều tra xử lý kịp thời và thông báo cho khách hàng được biết.</p>\n" +
                "<p><br>Bảo mật tuyệt đối mọi thông tin đặt hàng trực tuyến của khách hàng bao gồm thông tin hóa đơn kế toán chứng từ số hóa trên <strong>KFC Việt Nam</strong>.</p>\n" +
                "<p><br>Ban quản lý <strong>KFC Việt Nam</strong> yêu cầu các cá nhân khi đăng ký/ mua hàng là khách hàng, phải cung cấp đầy đủ thông tin cá nhân có liên quan như: Họ và tên, địa chỉ liên lạc, email, điện thoại…và chịu trách nhiệm về tính pháp lý của những thông tin trên. Ban quản lý <strong>KFC Việt Nam</strong> không chịu trách nhiệm cũng như không giải quyết mọi khiếu nại có liên quan đến quyền lợi của khách hàng đó nếu xét thấy tất cả thông tin cá nhân của khách hàng đó cung cấp khi đăng ký ban đầu là không chính xác.</p>\n" +
                "</body>\n" +
                "</html>\n";
    }
}
