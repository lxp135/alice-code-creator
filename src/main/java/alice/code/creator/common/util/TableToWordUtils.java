package alice.code.creator.common.util;

import alice.code.creator.domain.model.generator.Column;
import alice.code.creator.domain.model.generator.ColumnGenerator;
import alice.code.creator.domain.model.generator.DatabaseTable;
import cn.hutool.core.util.StrUtil;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.rtf.RtfWriter2;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;

public class TableToWordUtils {

    /**
     * 生成word文档
     *
     * @param tables ：该数据库下所有表信息
     * @param title :文件内容标题
     * @return ByteArrayOutputStream 文档流
     */
    public ByteArrayOutputStream toWord(List<DatabaseTable> tables,  String title) {

        // 输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {

            Document document = new Document(PageSize.A4);

            // 把word与输出流绑定
            RtfWriter2.getInstance(document, byteArrayOutputStream);

            // 开始写入word，必须调用这个方法才能进行word的写入
            document.open();

            Paragraph ph = new Paragraph();
            Font f = new Font();
            Paragraph p = new Paragraph(title, new Font(Font.NORMAL, 24, Font.BOLDITALIC, new Color(0, 0, 0)));
            p.setAlignment(1);
            document.add(p);
            ph.setFont(f);

            for (int i = 0; i < tables.size(); i++) {
                String tableName = tables.get(i).getTableName();
                String tableComment = tables.get(i).getTableComment();
                List<ColumnGenerator> fileds = tables.get(i).getColumnList();
                String all = "" + (i + 1) + " 表名称:" + tableName + "（" + tableComment + "）";

                // 声明一个5列的表格
                Table table = new Table(5);

                document.add(new Paragraph(""));

                // 设置表格宽度
                table.setBorderWidth(1);
                table.setPadding(0);
                table.setSpacing(0);

                //添加表头的元素，并设置表头背景的颜色
                Color chade = new Color(176, 196, 222);

                Cell cell = new Cell("字段名");
                addCell(table, cell, chade);
                cell = new Cell("类型");
                addCell(table, cell, chade);
                cell = new Cell("是否非空");
                addCell(table, cell, chade);
                cell = new Cell("是否主键");
                addCell(table, cell, chade);
                cell = new Cell("注释");
                addCell(table, cell, chade);

                table.endHeaders();

                // 表格的主体
                for (int k = 0; k < fileds.size(); k++) {
                    addContent(table, cell, fileds.get(k).getColumnName());
                    if(StrUtil.isNotBlank(fileds.get(k).getMaxLength())){
                        addContent(table, cell, fileds.get(k).getDataType()+"("+fileds.get(k).getMaxLength()+")");
                    }else{
                        addContent(table, cell, fileds.get(k).getDataType());
                    }
                    addContent(table, cell, fileds.get(k).getIsNullable().equals("YES") ? "否" : "是");
                    addContent(table, cell, !fileds.get(k).getColumnKey().equals("")  ? "是" : "否");
                    addContent(table, cell, fileds.get(k).getColumnComment());
                }
                Paragraph pheae = new Paragraph(all);
                //写入表说明
                document.add(pheae);
                //生成表格
                document.add(table);
            }
            // 关闭word文档对象,调用这个方法之后就无法添加内容到这个doc对象了.
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream;
    }

    /**
     * 添加表头到表格
     *
     * @param table
     * @param cell
     * @param chade
     */
    private void addCell(Table table, Cell cell, Color chade) {
        // 水平对齐方式
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(chade);
        table.addCell(cell);
    }

    /**
     * 添加内容到表格
     *
     * @param table
     * @param content
     */
    private void addContent(Table table, Cell cell, String content) {
        cell = new Cell(content);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

}
