package com.example.shop.utils.dialogs;

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

public class CustomDialogOperationPolicy extends Dialog {

    TextView mTxtViewOperationPolicy;
    ImageButton mImgButtonOperationPolicy;
    private String policy;
    public CustomDialogOperationPolicy(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_operation_policy);
        mTxtViewOperationPolicy = findViewById(R.id.textViewOperationPolicy);
        mTxtViewOperationPolicy.setMovementMethod(LinkMovementMethod.getInstance());

        mImgButtonOperationPolicy = findViewById(R.id.imageButtonCloseOperationPolicy);
        mImgButtonOperationPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        addPolicy();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            mTxtViewOperationPolicy.setText(Html.fromHtml(policy,Html.FROM_HTML_MODE_COMPACT));
        } else {
            mTxtViewOperationPolicy.setText(Html.fromHtml(policy));
        }

    }

    private void addPolicy() {
        policy = "<html>\n" +
                "<body>\n" +
                "<h2 style=\"text-align: center;\"><strong>Chính sách hoạt động</strong></h2>\n" +
                "<p><em><strong><br>A. ĐỐI VỚI WEBSITE</strong></em></p>\n" +
                "<p><strong><em><br>1/ Hướng dẫn đặt phần ăn</em></strong><br /><br />Để đặt phần ăn trên website <strong><u><a href=\"http://kfcvietnam.com.vn/\" target=\"_blank\" rel=\"noopener\">kfcvietnam.com.vn</a></u></strong>, Khách hàng có thể thực hiện theo 02 cách thức như sau:</p>\n" +
                "<p><em><u><br>Cách 1: Đặt phần ăn trực tuyến theo các bước trên website </u></em><strong><em><u><a href=\"http://kfcvietnam.com.vn/\" target=\"_blank\" rel=\"noopener\">kfcvietnam.com.vn</a></u></em></strong><strong><em><u>:</u></em></strong><br /><br />- Khách hàng chọn phần ăn mong muốn được hiển thị chi tiết tại chuyên mục<strong>“Thực Đơn”</strong>.<br /><br />- Các phần ăn mong muốn sẽ được ghi nhận tại <strong>“Phần ăn đã chọn”</strong>, trong đó liệt kê chi tiết về phần ăn, giá từng phần ăn và tổng giá của đơn hàng. Khách hàng có thể thay đổi phần ăn (thêm, bớt phần ăn) tại giai đoạn này.<br /><br />- Khi đã hoàn tất đơn hàng, Khách hàng chọn <strong>“Đặt hàng”</strong>.<br /><br />- Tại khu vực <strong>“Thông tin giao hàng</strong>”, khách hàng liệt kê các thông tin cần thiết bao gồm: họ và tên, địa chỉ, tỉnh/ thành phố, số điện thoại, email và chọn <strong>“Giao hàng đến địa chỉ này”</strong>.</p>\n" +
                "<p><br>- Tại trang <strong>“Xác nhận đơn hàng”</strong>, khách hàng dò lại thông tin đặt hàng và chọn nút <strong>“Đồng ý đặt hàng”</strong> để hoàn tất đơn hàng.</p>\n" +
                "<p><br>- Hệ thống sẽ gửi email về đơn hàng của khách hàng trong vòng 05-10 phút và sẽ liên hệ lại để xác nhận trong vòng 15 phút kể từ khi khách hàng chọn nút <strong>“Đồng ý đặt hàng”</strong> trên website.<br /><br /><em><u>Cách 2: Gọi điện thoại đến tổng đài 19006886:</u></em><br /><br />- Khách hàng liên hệ đến tổng đài để được các tổng đài viên hỗ trợ.<br /><br />- Khách hàng gọi tên phần ăn được cung cấp trên website, tờ rơi hoặc các catalogue giới thiệu phần ăn của KFC Việt Nam để nhân viên tổng đài tiếp nhận, tiếp đó Khách hàng sẽ được tổng đài viên yêu cầu cung cấp các thông tin giao hàng bao gồm: Họ và tên, địa chỉ, tỉnh/ thành phố, số điện thoại, email, các yêu cầu giao hàng khác (nếu có).<br /><br />- Nhân viên của KFC Việt Nam tiếp nhận đơn hàng, xác nhận đơn đặt hàng và giao hàng trong vòng 30 phút trở lên kể từ thời điểm tiếp nhận đơn hàng.<br /><br />* <strong>Lưu ý:</strong> Khi đặt hàng trên website <strong><u><a href=\"http://kfcvietnam.com.vn/\" target=\"_blank\" rel=\"noopener\">kfcvietnam.com.vn</a></u></strong>, Khách hàng hiểu và chấp nhận các điều kiện/ lưu ý sau:<br /> <br /> - KFC Việt Nam chỉ tiếp nhận đơn hàng trực tuyến từ <strong>10:00 </strong>sáng đến <strong>22:00</strong> tối.<br /> <br /> - KFC Việt Nam chỉ tiếp nhận đơn hàng có tổng giá tối thiểu <strong>80.000</strong> đồng.<br /><br /><strong><em>2/ Xác nhận đơn hàng khi đặt hàng trực tuyến trên website</em></strong> <strong><em><u><a href=\"http://kfcvietnam.com.vn/\" target=\"_blank\" rel=\"noopener\">kfcvietnam.com.vn</a></u></em></strong><br /><br />Sau khi hoàn thành việc đặt hàng trực tuyến trên website <strong><u><a href=\"http://kfcvietnam.com.vn/\" target=\"_blank\" rel=\"noopener\">kfcvietnam.com.vn</a></u></strong>, hệ thống sẽ tự động gửi email xác nhận vào hòm thư của khách hàng.<br /><br />Trong vòng 10 phút, nhân viên trực tổng đài của KFC Việt Nam sẽ liên lạc xác nhận, xử lý, hoàn thành các thủ tục đặt hàng. Nếu không có hồi âm, khách hàng vui lòng gọi trực tiếp vào hotline <strong>19006886 </strong>để phản ánh trực tiếp về đơn hàng.<br /><br />Trong hầu hết trường hợp, khách hàng sẽ nhận được email cùng với thông tin đặt hàng trong vòng 10 phút kể từ lúc khách hàng thực hiện việc đặt hàng. Nếu Khách hàng không nhận được sau thời gian này, hãy kiểm tra thư rác hoặc các bộ lọc thư rác.</p>\n" +
                "<p><em><strong><br>B. ĐỐI VỚI ỨNG DỤNG “KFC VIETNAM”</strong></em></p>\n" +
                "<p><em><strong><br>1/ Hướng dẫn đặt phần ăn</strong></em></p>\n" +
                "<p><br>Để đặt phần ăn trên <em>ứng dụng “KFC Vietnam”</em>, Khách hàng thực hiện theo các bước như sau:</p>\n" +
                "<p><br>- Bước 1: Sau khi khởi chạy ứng dụng, khách hàng lựa chọn ngôn ngữ hiển thị của ứng dụng (tiếng Việt / tiếng Anh).Tiếp đến, khách hàng chọn Tỉnh/Thành phố nơi khách hàng đang cư trú để hệ thống xác định các địa điểm sẵn sàng phục vụ.</p>\n" +
                "<p><br>- Bước 2: Tại giao diện “Thực đơn”, khách hàng chọn Nhóm các phần ăn mong muốn. Tại mỗi Nhóm phần ăn, từng loại phần ăn cụ thể sẽ được hiển thị theo hình ảnh minh họa và đơn giá.</p>\n" +
                "<p><br>- Bước 3: Tại giao diện hiển thị chi tiết về phần ăn, khách hàng lựa chọn các thông tin bổ sung cho phần ăn (theo nhu cầu) và xác định số lượng phần ăn cần đặt. Các phần ăn mong muốn sẽ được ghi nhận tại <strong>“Giỏ hàng”</strong>, trong đó liệt kê chi tiết về phần ăn, giá từng phần ăn và tổng giá của đơn hàng. Khách hàng có thể thay đổi phần ăn (thêm, bớt phần ăn) tại giai đoạn này.</p>\n" +
                "<p><br>- Bước 4: Khi đã hoàn tất đơn hàng, Khách hàng chọn <strong>“Đặt hàng”</strong>.</p>\n" +
                "<p><br>Tại khu vực <strong>“Thông tin giao hàng</strong>”, khách hàng liệt kê các thông tin cần thiết bao gồm: họ và tên, địa chỉ, tỉnh/ thành phố, số điện thoại, email và chọn <strong>“Giao hàng đến địa chỉ này”</strong>.</p>\n" +
                "<p><br>Tại giao diện <strong>“Xác nhận đơn hàng”</strong>, khách hàng dò lại thông tin đặt hàng và chọn nút <strong>“Đồng ý đặt hàng”</strong> để hoàn tất đơn hàng.</p>\n" +
                "<p><br>Hệ thống sẽ gửi email về đơn hàng của khách hàng trong vòng 05-10 phút và sẽ liên hệ lại để xác nhận trong vòng 15 phút kể từ khi khách hàng chọn nút <strong>“Đồng ý đặt hàng”</strong> trên ứng dụng.</p>\n" +
                "<p><br>* <strong>Lưu ý:</strong> Khi đặt hàng trên ứng dụng <strong><a href=\"http://kfcvietnam.com.vn/\" target=\"_blank\" rel=\"noopener\">“KFC Vietnam”</a></strong>, Khách hàng hiểu và chấp nhận các điều kiện/ lưu ý sau:</p>\n" +
                "<p><br>- KFC Việt Nam chỉ tiếp nhận đơn hàng trực tuyến từ <strong>09:00 </strong>sáng đến <strong>21:00</strong> tối.</p>\n" +
                "<p><br>- KFC Việt Nam chỉ tiếp nhận đơn hàng có tổng giá tối thiểu <strong>80.000</strong> đồng.</p>\n" +
                "<p><em><strong><br>2/ Xác nhận đơn hàng khi đặt hàng trực tuyến trên ứng dụng</strong></em> <em><strong><a href=\"http://kfcvietnam.com.vn/\" target=\"_blank\" rel=\"noopener\">“KFC Vietnam”</a></strong></em>:</p>\n" +
                "<p><br>Sau khi hoàn thành việc đặt hàng trực tuyến trên ứng dụng <strong><a href=\"http://kfcvietnam.com.vn/\" target=\"_blank\" rel=\"noopener\">“KFC Vietnam”</a></strong>, hệ thống sẽ tự động gửi email xác nhận vào hòm thư của khách hàng.</p>\n" +
                "<p><br>Trong vòng 10 phút, nhân viên của KFC Việt Nam sẽ liên lạc xác nhận, xử lý, hoàn thành các thủ tục đặt hàng cho khách hàng. Nếu không có hồi âm, khách hàng vui lòng gọi trực tiếp vào hotline <strong>19006886 </strong>để phản ánh trực tiếp về đơn hàng.</p>\n" +
                "<p><br>Trong hầu hết trường hợp, khách hàng sẽ nhận được email cùng với thông tin đặt hàng trong vòng 10 phút kể từ lúc khách hàng thực hiện việc đặt hàng. Nếu Khách hàng không nhận được sau thời gian này, hãy kiểm tra thư rác hoặc các bộ lọc thư rác.</p>\n" +
                "<p><strong><br>C. CÁC CHÍNH SÁCH CHUNG KHÁC</strong></p>\n" +
                "<p><em><strong><br>1/ Chính sách thanh toán khi đặt hàng</strong></em></p>\n" +
                "<p><br>Thanh toán trực tiếp tại nơi giao hàng.</p>\n" +
                "<p><br>Sau khi đặt hàng, nhân viên giao hàng của KFC Việt Nam sẽ thông báo cho khách hàng về việc giao hàng. Khách hàng vui lòng thanh toán bằng tiền mặt Việt Nam đồng (VNĐ) hoặc phiếu quà tặng (nếu có) tại nơi giao hàng, người giao hàng sẽ cung cấp hóa đơn bán hàng hợp lệ sau khi khách hàng kiểm tra đơn hàng.</p>\n" +
                "<p><em><strong><br>2/ Chính sách giao hàng – nhận hàng</strong></em>:</p>\n" +
                "<p><br>Sau khi tiếp nhận đơn hàng, nhân viên giao hàng KFC Việt Nam sẽ giao hàng đến địa chỉ do khách hàng cung cấp trong vòng 30 phút trở lên. Tại thời điểm giao hàng, khách hàng kiểm tra phần ăn theo đơn hàng ghi trên hóa đơn và số tiền cần thanh toán. Khách hàng vui lòng thanh toán bằng tiền mặt hoặc phiếu quà tặng (nếu có). Việc giao hàng kết thúc khi khách hàng xác nhận đủ phần ăn.</p>\n" +
                "<p><br><strong>* Các lưu ý:</strong></p>\n" +
                "<p><br>- Thời gian giao hàng có thể nhanh hơn hoặc lâu hơn với dự kiến vì lý do thời tiết, đơn hàng tại cửa hàng hiện quá tải, địa chỉ do khách hàng cung cấp quá xa với cửa hàng hoặc địa chỉ của khách hàng bị nhầm lẫn với các địa chỉ khác. Lúc này, KFC Việt Nam sẽ thông báo cụ thể đến khách hàng ngay khi phát sinh sự kiện gây chậm trễ việc giao hàng.</p>\n" +
                "<p><br>- Trường hợp khách hàng thay đổi địa chỉ hoặc yêu cầu điều chỉnh đơn hàng chỉ được thực hiện trong vòng 3 phút từ khi xác nhận phần ăn và địa chỉ giao hàng.</p>\n" +
                "<p><br>- Việc điều chỉnh đơn hàng nhằm thay đổi phần ăn sẽ không được chấp nhận nếu khách hàng thông báo điều chỉnh sau 3 phút kể từ khi đơn hàng đã được KFC Việt Nam tiếp nhận.</p>\n" +
                "<p><em><strong><br>3/ Chính sách đổi, trả hàng</strong></em>: </p>\n" +
                "<p><em><u><br>3.1. Chính sách đổi hàng:</u></em></p>\n" +
                "<p><br>- Sau khi yêu cầu đơn hàng, khách hàng có thể liên hệ tổng đài để yêu cầu nhân viên tổng đài của KFC Việt Nam điều chỉnh đơn hàng trong vòng 3 phút kể từ khi đơn hàng đã được xác nhận. Mọi trường hợp điều chỉnh đơn hàng quá thời gian nêu trên đều không được chấp nhận.</p>\n" +
                "<p><br>- Trường hợp đơn hàng sau khi điều chỉnh có tổng giá trị thấp hơn <strong>80.000</strong> đồng KFC sẽ từ chối việc điều chỉnh này.</p>\n" +
                "<p><br>- Trường hợp khách hàng phát hiện phần ăn bị nhầm lẫn do lỗi của KFC, KFC sẽ tiến hành ngay việc đổi hàng đúng theo đơn hàng đã tiếp nhận.</p>\n" +
                "<p><br>Trường hợp khách hàng phát hiện phần ăn bị hư, hỏng, không đạt chất lượng… KFC sẽ tiến hành ngay việc kiểm tra và đổi hàng cho khách hàng trong vòng 30 phút kể từ khi nhận được phản ánh từ khách hàng.</p>\n" +
                "<p><em><u><br>3.2. Chính sách trả hàng:</u></em></p>\n" +
                "<p><br>Trong trường hợp phần ăn phát hiện có sự hư, hỏng, ôi, thiu… Khách hàng vui lòng thông báo ngay cho KFC Việt Nam qua số điện thoại <strong>19006886</strong> để phản ánh, KFC Việt Nam sẽ cử nhân viên đến kiểm tra trực tiếp tại địa chỉ của khách hàng và thực hiện việc đổi hàng, nếu khách hàng không đồng ý với việc đổi hàng, KFC Việt Nam sẽ hoàn tiền cho khách hàng tương ứng với giá trị phần ăn bị hư, hỏng.</p>\n" +
                "</body>\n" +
                "</html>\n";
    }
}
