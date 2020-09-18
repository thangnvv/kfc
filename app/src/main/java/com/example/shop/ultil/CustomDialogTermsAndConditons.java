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

public class CustomDialogTermsAndConditons extends Dialog {

    TextView mTxtViewTermsAndConditions;
    ImageButton mImgButtonCloseTermsAndConditions;
    private String policy;
    public CustomDialogTermsAndConditons(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_terms_and_conditons);
        mTxtViewTermsAndConditions = findViewById(R.id.textViewTermsAndConditions);
        mTxtViewTermsAndConditions.setMovementMethod(LinkMovementMethod.getInstance());

        mImgButtonCloseTermsAndConditions = findViewById(R.id.imageButtonCloseTermsAndConditons);
        mImgButtonCloseTermsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        addPolicy();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            mTxtViewTermsAndConditions.setText(Html.fromHtml(policy,Html.FROM_HTML_MODE_COMPACT));
        } else {
            mTxtViewTermsAndConditions.setText(Html.fromHtml(policy));
        }

    }

    private void addPolicy() {
        policy = "<html>\n" +
                "<body>\n" +
                "<h2 style=\"text-align: center;\"><strong>Chính sách và quy định chung</strong></h2>\n" +
                "<p><br><strong>1.1 Chấp nhận chính sách và quy định chung khi sử dụng website</strong></p>\n" +
                "<p><br>Những chính sách và quy định chung này ảnh hưởng đến việc Khách hàng sử dụng website của KFC Việt Nam và các tổ chức có liên quan đến website của KFC Việt Nam.</p>\n" +
                "<p><br>Khi truy cập hoặc đăng nhập vào website này có nghĩa là khách hàng đã chấp nhận và đồng ý với các chính sách và quy định chung này và những chính sách và quy định của chính sách bảo mật thông tin từ KFC Việt Nam.</p>\n" +
                "<p><br>Nếu khách hàng không đồng ý với bất kỳ điều khoản nào dưới đây, vui lòng không truy cập vào website của KFC Việt Nam. Chính sách và quy định chung này được xem là hợp đồng giữa Khách hàng và KFC Việt Nam và được áp dụng khi Khách hàng sử dụng website của KFC Việt Nam. Những chính sách và quy định chung này ảnh hưởng đến quyền lợi của Khách hàng, vui lòng đọc kỹ trước khi sử dụng.</p>\n" +
                "<p><br><strong>1.2 Những thay đổi đối với các chính sách và quy định chung này</strong></p>\n" +
                "<p><br>KFC Việt Nam có quyền thay đổi các chính sách và quy định chung này bất cứ lúc nào mà không cần thông báo trước với khách hàng. Khách hàng có thể xem phiên bản cập nhật mới nhất của chính sách và quy định chung này bằng cách nhấp chuột vào <strong>\"Chính Sách và Quy Định Chung\"</strong> tại trang chủ của <strong><a href=\"http://kfcvietnam.com.vn/\">kfcvietnam.com.vn</a></strong>. Phiên bản cập nhật này sẽ thay thế tất cả các phiên bản cũ. Nếu khách hàng sử dụng website của KFC Việt Nam sau khi xuất hiện những thay đổi này có nghĩa là khách hàng đã đồng ý với những thay đổi này.</p>\n" +
                "<p><br><strong>1.3 Tài khoản sử dụng của Khách hàng</strong></p>\n" +
                "<p><br>Nếu khách hàng sử dụng website của KFC Việt Nam, khách hàng có trách nhiệm duy trì tính bảo mật của tài khoản do khách hàng sử dụng, bảo quản mật mã và hạn chế cho người khác truy cập vào máy tính của khách hàng, và khách hàng đồng ý chịu trách nhiệm cho tất cả mọi hoạt động diễn ra trong tài khoản hoặc mật mã của khách hàng.</p>\n" +
                "<p><br><strong>1.4 Website của KFC Việt Nam</strong></p>\n" +
                "<p><br>Những chính sách và quy định chung này áp dụng cho tất cả Khách hàng sử dụng website của KFC Việt Nam. Website này có thể chứa những liên kết đến website của bên thứ ba không thuộc sở hữu của KFC Việt Nam. KFC Việt Nam không có quyền kiểm soát nội dung, chính sách bảo mật thông tin hoặc những phần khác trong các website của bên thứ ba. Hơn nữa, KFC Việt Nam sẽ không thể kiểm duyệt hoặc biên tập lại nội dung trên các website của bên thứ ba. Chúng tôi khuyến khích khách hàng đọc kỹ các điều khoản, điều kiện và chính sách bảo mật thông tin của các website của bên thứ ba mà bạn truy cập.</p>\n" +
                "<p><br><strong>1.5 Truy cập vào website</strong></p>\n" +
                "<p><br>KFC Việt Nam chấp thuận cho bạn sử dụng website như đã công bố trong điều khoản sử dụng dịch vụ, bao gồm: (i) sử dụng website cho mục đích cá nhân, không nhằm mục đích thương mại; (ii) không sao chép hoặc cung cấp bất cứ thông tin nào của website này cho bên thứ ba; (iii) Khách hàng không được thay đổi, chỉnh sửa bất cứ phần nào của website này; (iv) Khách hàng phải tuân theo chính sách và quy định chung sử dụng dịch vụ.</p>\n" +
                "<p><br>Để truy cập vào một số tính năng của website, khách hàng phải tạo một tài khoản cho riêng mình. Khách hàng không được phép sử dụng tài khoản của người khác nếu không được sự đồng ý của chủ tài khoản. Khi tạo tài khoản, khách hàng phải cung cấp thông tin đầy đủ và chính xác. Khách hàng tự chịu trách nhiệm cho tất cả các hoạt động xảy ra trong tài khoản của mình, và khách hàng phải giữ mật mã tài khoản an toàn. Khách hàng phải lập tức thông báo cho KFC Việt Nam nếu có bất cứ vi phạm nào về độ an toàn hoặc sử dụng tài khoản mà không có sự đồng ý của chủ tài khoản. Mặc dù KFC Việt Nam không chịu trách nhiệm pháp lý cho bất cứ những mất mát nào liên quan đến việc sử dụng tài khoản của khách hàng một cách bất hợp pháp, khách hàng phải chịu trách nhiệm cho những mất mát của KFC Việt Nam hoặc những mất mát do sử dụng bất hợp pháp.</p>\n" +
                "<p><br>Khách hàng đồng ý không sử dụng bất cứ hệ thống tự động nào, bao gồm nhưng không giới hạn, người máy, gián điệp...để truy cập vào website và gửi nhiều thông tin yêu cầu đến máy chủ của KFC Việt Nam nhiều hơn một người bình thường có thể làm được bằng việc sử dụng website thông thường trong một khoản thời gian nhất định. Mặc dù đã được đề cập ở trên, KFC Việt Nam chấp thuận cho người điều hành của các công cụ tìm kiếm công cộng được phép sử dụng gián điệp để sao chép thông tin từ website cho mục đích tạo ra những chỉ số tìm kiếm có giá trị công cộng của các thông tin này nhưng không được giữ hoặc lưu trữ những tài liệu này. KFC Việt Nam có quyền thu hồi những tài liệu này ngoại trừ những trường hợp đặc biệt. Khách hàng đồng ý không thu thập bất cứ thông tin cá nhân nào từ website, bao gồm tên tài khoản, và cũng không sử dụng những hệ thống truyền thông do website cung cấp cho mục đích thương mại nào. Khách hàng đồng ý không van nài, với mục đích thương mại, bất kỳ người sử dụng nào của website để yêu cầu đăng ký thông tin.</p>\n" +
                "<p><br><strong>1.6 Đăng ký thông tin của người sử dụng</strong></p>\n" +
                "<p><br>Website của KFC Việt Nam cho phép đăng ký thông tin cá nhân. Khách hàng nên hiểu rằng những thông tin đăng ký này có được công bố hay không, KFC Việt Nam cũng không đảm bảo rằng sẽ bảo mật thông tin cho bất cứ phần đăng ký nào. Khách hàng phải đồng ý với việc KFC có thể công bố tên của khách hàng và phần đăng ký thông tin trên website của KFC Việt Nam hoặc trong những phần thông cáo báo chí hoặc trong các phương tiện truyền thông khác.</p>\n" +
                "<p><br>Khách hàng sẽ đơn phương chịu trách nhiệm cho phần đăng ký thông tin cá nhân của mình và tầm quan trọng của việc công bố những thông tin này. Để kết nối với phần đăng nhập thông tin, khách hàng xác nhận và cam kết cho: (i) quyền sở hữu hoặc có những giấy phép cần thiết, chấp thuận và cho phép KFC Việt Nam sử dụng tất cả các bằng sáng chế, thương hiệu, bản quyền, nhãn hiệu đã đăng ký bản quyền và tất cả các quyền lợi độc quyền khác trong tất cả các phần đăng ký thông tin và (ii) Khách hàng có văn bản chấp thuận, biên nhận, và sự đồng ý của mỗi cá nhân đăng ký thông tin trong phần đăng ký được sử dụng tên hoặc chân dung cá nhân và những phần đăng ký này mặc nhiên trở thành tài sản của KFC Việt Nam. KFC Việt Nam có thể tự do sử dụng những phần đăng ký thông tin này. Để rõ ràng hơn, khách hàng có thể giữ quyền sở hữu cá nhân trong các phần đăng ký thông tin khác. Tuy nhiên, khi đăng ký thông tin của mình trên website của KFC Việt Nam là khách hàng đã đồng ý cho KFC Việt Nam sử dụng những thông tin cá nhân này và KFC Việt Nam có quyền sử dụng những thông tin này cho mục đích quảng cáo trên website và các kênh truyền thông khác.</p>\n" +
                "<p><br><strong>1.7 Miễn trừ trách nhiệm về kỹ thuật</strong></p>\n" +
                "<p><br>Khách hàng đồng ý rằng khi sử dụng website của KFC Việt Nam, khách hàng sẽ tự chấp nhận các rủi ro có thể xảy ra. Các nhân viên văn phòng, ban giám đốc, nhân viên và các đại lý của KFC Việt Nam từ chối bảo hành có liên quan đến website và việc sử dụng website của khách hàng. KFC Việt Nam không bảo hành tính chính xác hoặc hoàn thiện của nội dung trên website hoặc nội dung của bất kỳ website nào được kết nối với website của KFC Việt Nam và chúng tôi không có quyền và nghĩa vụ cho bất cứ (i) những nội dung sai sót và sơ suất hoặc không chính xác, (ii) những tổn thương cá nhân hoặc hư hại tài sản đến từ tự nhiên trong quá trình truy cập và sử dụng website của chúng tôi, (iii) truy cập bất hợp pháp vào các máy chủ của chúng tôi để lấy thông tin cá nhân hoặc/và những thông tin tài chính mà chúng tôi bảo quản trong đó, (iv) cắt ngang hoặc chấm dứt truyền thông tin đến website của chúng tôi, (v) sai sót, vi-rút, hoặc những thứ tương tự như vậy có thể truyền đến thông qua website của chúng tôi bởi bên thứ ba, và (vi) lỗi hoặc sai sót trong phần nội dung hoặc những mất mát hư hỏng do sử dụng nội dung đăng tải, email, đường truyền hoặc những thứ tương tự từ website của KFC Việt Nam. KFC Việt Nam sẽ không bảo hành và chịu trách nhiệm bất cứ sản phẩm, dịch vụ nào hoặc các chương trình khuyến mãi của bên thứ ba thông qua website của KFC Việt Nam và KFC Việt Nam cũng không có trách nhiệm giám sát bất cứ giao dịch nào của Khách hàng với bên thứ ba, những người cung cấp sản phẩm và dịch vụ. Việc mua sản phẩm và dịch vụ thông qua trung gian, tốt nhất là khách hàng nên sử dụng khả năng phán xét của mình để quyết định thế nào là hợp lý.</p>\n" +
                "<p><br><strong>1.8 Hạn chế về nghĩa vụ pháp lý</strong></p>\n" +
                "<p><br>Ban giám đốc, nhân viên hoặc các đại lý của KFC Việt Nam không chịu bất cứ trách nhiệm pháp lý nào cho khách hàng trong bất kỳ trường hợp nào bao gồm trực tiếp, gián tiếp, ngẫu nhiên, đặc biệt, hư hỏng do cố ý hay bất kỳ vấn đề gì là kết quả của các vấn đề sau bao gồm nhưng không giới hạn: (i) những nội dung sai sót hoặc không chính xác, (ii) những tổn thương cá nhân hoặc hư hại tài sản đến từ tự nhiên trong quá trình truy cập và sử dụng website của chúng tôi, (iii) truy cập bất hợp pháp vào các máy chủ của chúng tôi để lấy thông tin cá nhân hoặc/và những thông tin tài chính mà chúng tôi bảo quản trong đó, (iv) cắt ngang hoặc chấm dứt truyền thông tin đến website của chúng tôi, (v) sai sót, vi-rút, hoặc những thứ tương tự như vậy có thể truyền đến thông qua website của chúng tôi bởi bên thứ ba, và (vi) lỗi hoặc sai sót trong phần nội dung hoặc những mất mát hư hỏng do sử dụng nội dung đăng tải, email, đường truyền hoặc những thứ tương tự từ website của KFC Việt Nam, dựa trên bảo hành, hợp đồng, sai lầm dân sự, hoặc bất cứ các lý thuyết pháp lý khác, công ty sẽ không chịu trách nhiệm cho những thiệt hại có thể xảy ra này. Các hạn chế về nghĩa vụ pháp lý như đã nói ở trên sẽ được áp dụng trong phạm vi luật pháp cho phép.</p>\n" +
                "<p><br>Khách hàng nên nhận thức rõ việc KFC Việt Nam sẽ không chịu trách nhiệm cho việc đăng ký thông tin của người sử dụng, hoặc những chỉ dẫn bất hợp pháp của bất kỳ bên thứ ba nào hoặc bất cứ những rủi ro thiệt hại nào khác cho khách hàng.</p>\n" +
                "<p><br><strong>1.9 Bồi thường</strong></p>\n" +
                "<p><br>Khách hàng đồng ý bảo vệ, bồi thường và không gây hại cho KFC Việt Nam, công ty mẹ, ban giám đốc, nhân viên chi nhánh và các đại lý dựa trên toàn bộ/bất cứ các yêu sách, thiệt hại, mất mát, nghĩa vụ, chi phí hoặc nợ (bao gồm nhưng không giới hạn phí công chứng) phát sinh do: (i) Khách hàng truy cập & sử dụng website của KFC Việt Nam, (ii) Khách hàng vi phạm bất cứ điều khoản nào trong phần điều khoản sử dụng dịch vụ này, (iii) Khách hàng vi phạm quyền lợi của bất kỳ bên thứ ba nào, bao gồm nhưng không giới hạn tác quyền, tài sản, hoặc là quyền cá nhân, (iv) bất cứ các yêu sách nào từ phần đăng ký thông tin cá nhân gây thiệt hại cho bên thứ ba. Trách nhiệm về bồi thường sẽ được áp dụng xuyên suốt trong các điều khoản sử dụng cũng như việc truy cập vào website của KFC Việt Nam.</p>\n" +
                "<p><br><strong>1.10 Tài sản trí tuệ của KFC Việt Nam</strong></p>\n" +
                "<p><br>Website này chứa nhiều bản quyền thương hiệu có giá trị do KFC Việt Nam và các chi nhánh, thành viên trên toàn thế giới sở hữu và sử dụng. Những bản quyền thương hiệu này được sử dụng để phân biệt các chất lượng sản phẩm và dịch vụ của KFC Việt Nam. Những bản quyền thương hiệu này và các tài sản có liên quan được bảo vệ để tránh không được tái sản xuất và giả mạo theo luật quốc gia và luật quốc tế và không được sao chép dưới bất kỳ hình thức nào nếu không được sự đồng ý bằng văn bản của KFC Việt Nam. Văn bản, hình ảnh minh họa, mã html có trong website này sẽ không được sao chép, phân phối, trưng bày, tái sản xuất và truyền tải dưới bất kỳ hình thức nào hoặc phương tiện nào mà không được sự đồng ý bằng văn bản của KFC Việt Nam. Website của KFC Việt Nam có thể liên kết đến những trang khác không liên quan đến KFC Việt Nam. Các đường kết nối không được cung cấp dưới dạng dịch vụ dành cho Khách hàng và không được tài trợ hoặc liên kết với website của KFC Việt Nam. KFC Việt Nam sẽ không xem xét các đường kết nối này đến từ website nào và không chịu trách nhiệm cho nội dung của bất cứ website nào khác. Khách hàng tự chịu rủi ro khi truy cập các đường kết nối này. KFC Việt Nam sẽ không đại diện hoặc bảo hành cho bất cứ nội dung, tính trọn vẹn, hoặc tính chính xác nào của các đường kết nối này hoặc các website liên kết với website của chúng tôi.</p>\n" +
                "<p><br>KFC Việt Nam có quyền điều chỉnh các Điều khoản dịch vụ này bất cứ lúc nào mà không cần thông báo trước, và nhiệm vụ của khách hàng là phải xem lại các điều khoản này nếu có bất kỳ thay đổi nào. Khách hàng sử dụng website của KFC Việt Nam với những điều khoản chỉnh sửa có nghĩa là khách hàng đã đồng ý và chấp nhận những điều khoản chỉnh sửa này.</p>" +
                "</body>\n" +
                "</html>\n";
    }
}
