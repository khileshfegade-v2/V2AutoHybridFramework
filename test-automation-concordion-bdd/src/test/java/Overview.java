import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import com.v2solutions.common.*;
import java.util.*;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.concordion.ext.runtotals.RunTotalsExtension;
import org.concordion.api.extension.Extensions;
import org.concordion.ext.LoggingTooltipExtension;
import org.concordion.ext.inputstyle.InputStyleExtension;
import org.concordion.ext.ScreenshotExtension;
import org.concordion.ext.TimestampFormatterExtension;

@RunWith(ConcordionRunner.class)
@Extensions({RunTotalsExtension.class,LoggingTooltipExtension.class,InputStyleExtension.class,ScreenshotExtension.class,TimestampFormatterExtension.class})
public class Overview {
}